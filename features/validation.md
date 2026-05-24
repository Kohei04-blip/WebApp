# 📘 今日やったバリデーション整理

## 1. 目的

```text
入力値をチェックして、不正なデータを登録・更新させない
```

今回は主に、

```text
タイトルが空ならエラーにする
```

を実装した。

---

# 2. 変更した主なファイル

```text
dto/
  └ NodeForm.java

controller/
  └ NodeController.java

templates/nodes/
  ├ new.html
  └ edit.html

build.gradle
```

---

# 3. build.gradle の変更

`@NotBlank` を使うために追加した。

```gradle
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

ポイント：

```text
@NotBlank や @Valid は validation ライブラリが必要
```

---

# 4. NodeForm.java を作成

```java
package com.example.reviewapp.dto;

import jakarta.validation.constraints.NotBlank;

public class NodeForm {

    @NotBlank(message = "タイトルを入力してください")
    private String title;

    private String content;

    // getter / setter
}
```

## 役割

```text
画面の入力値を受け取る専用クラス
```

## 重要ポイント

```text
Entity：DB保存用
DTO/Form：画面入力用
```

---

# 5. Controllerの変更

## 新規登録

```java
@PostMapping
public String create(@Valid NodeForm nodeForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
        return "nodes/new";
    }

    nodeService.create(nodeForm.getTitle(), nodeForm.getContent());
    return "redirect:/nodes";
}
```

## 編集更新

```java
@PostMapping("/{id}")
public String update(
        @PathVariable Long id,
        @Valid NodeForm nodeForm,
        BindingResult bindingResult,
        Model model) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("nodeId", id);
        return "nodes/edit";
    }

    nodeService.update(id, nodeForm.getTitle(), nodeForm.getContent());
    return "redirect:/nodes";
}
```

## 重要ポイント

```text
@Valid：入力チェックを実行
BindingResult：エラー結果を受け取る
hasErrors()：エラーがあるか確認
```

---

# 6. new.html の変更

```html
<form th:action="@{/nodes}" th:object="${nodeForm}" method="post">
```

```html
<input type="text" th:field="*{title}" class="form-control">

<div class="text-danger mt-1"
     th:if="${#fields.hasErrors('title')}"
     th:errors="*{title}">
</div>
```

## 重要ポイント

```text
th:object：フォームとNodeFormをつなぐ
th:field：入力欄とフィールドをつなぐ
th:errors：エラーメッセージを表示
```

---

# 7. edit.html の変更

@Valid対応後は、`node` ではなく以下を使いました。

```text
nodeForm
nodeId
```

正しいform：

```html
<form th:action="@{/nodes/{id}(id=${nodeId})}" th:object="${nodeForm}" method="post">
```

## 今日の重要エラー

以前はこうなっていた。

```html
id=${node.id}
```

しかしControllerでは `node` を渡していなかったのでエラーになった。

正しくは：

```html
id=${nodeId}
```

---

# 8. 今日の一番重要な理解

```text
Controllerで渡した名前とHTMLで使う名前は完全一致させる
```

例：

```java
model.addAttribute("nodeForm", nodeForm);
model.addAttribute("nodeId", id);
```

HTML：

```html
th:object="${nodeForm}"
id=${nodeId}
```

---

# 9. 手動バリデーションとの違い

## 手動チェック

```java
if (title == null || title.isBlank()) {
}
```

## @Valid方式

```java
@Valid NodeForm nodeForm
```

```java
@NotBlank(message = "タイトルを入力してください")
```

## 違い

```text
手動：Controllerに条件を書く
@Valid：Formクラスにルールを書く
```

---

# 10. まとめ

```text
NodeForm：入力値を受け取る
@NotBlank：空欄禁止ルール
@Valid：チェック実行
BindingResult：エラー結果確認
th:object：HTMLとFormをつなぐ
th:field：入力欄と項目をつなぐ
th:errors：エラー表示
```

今日の核心はこれです。

```text
入力チェックの責任をControllerからNodeFormへ移した
```
