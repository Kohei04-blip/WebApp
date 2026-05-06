# 📘 編集機能（Update）まとめ

---

# 🎯 全体の流れ（超重要）

```text
① 一覧画面で「編集」クリック
↓
② /nodes/{id}/edit にアクセス
↓
③ Controllerがidを受け取る
↓
④ Serviceで対象Nodeを取得
↓
⑤ Modelに入れてHTMLへ渡す
↓
⑥ edit.htmlに既存データ表示
↓
⑦ フォーム編集 → POST送信
↓
⑧ Controllerが更新データを受け取る
↓
⑨ Serviceで更新処理
↓
⑩ DB更新
↓
⑪ /nodes にリダイレクト
↓
⑫ 一覧画面に反映
```

---

# 🧠 ディレクトリ構成

```text
controller/
  └ NodeController.java

service/
  └ NodeService.java

repository/
  └ NodeRepository.java

entity/
  └ Node.java

templates/nodes/
  ├ list.html
  ├ new.html
  └ edit.html ← 追加
```

---

# 🧠 各ファイルの役割

---

## 🟦 Controller（NodeController）

---

### ① 編集画面表示

```java
@GetMapping("/{id}/edit")
public String editForm(@PathVariable Long id, Model model){
    Node node = nodeService.findById(id);
    model.addAttribute("node", node);
    return "nodes/edit";
}
```

👉 idを受け取る
👉 データ取得
👉 HTMLに渡す

---

### ② 更新処理

```java
@PostMapping("/{id}")
public String update(@PathVariable Long id, String title, String content){
    nodeService.update(id, title, content);
    return "redirect:/nodes";
}
```

👉 入力データを受け取る
👉 Serviceへ渡す
👉 一覧へ戻る

---

## 🟩 Service（NodeService）

---

### ① 1件取得

```java
public Node findById(Long id) {
    return nodeRepository.findById(id).orElse(null);
}
```

---

### ② 更新処理

```java
public void update(Long id, String title, String content){
    Node node = nodeRepository.findById(id).orElse(null);

    node.setTitle(title);
    node.setContent(content);

    nodeRepository.save(node);
}
```

👉 既存データ取得
👉 値を上書き
👉 保存

---

## 🟨 Repository

```java
nodeRepository.findById(id);
nodeRepository.save(node);
```

👉 DB操作のみ

---

## 🟥 Entity（Node）

```java
private String title;
private String content;
```

```java
public void setTitle(String title)
public void setContent(String content)
```

👉 データの箱

---

# 🧠 HTMLの役割

---

## list.html（一覧）

```html
<li th:each="node : ${nodes}">
    <span th:text="${node.title}"></span>
    <a th:href="@{/nodes/{id}/edit(id=${node.id})}">編集</a>
</li>
```

👉 編集画面へ遷移

---

## edit.html（編集）

```html
<form th:action="@{/nodes/{id}(id=${node.id})}" method="post">

<input name="title" th:value="${node.title}">
<input name="content" th:value="${node.content}">

</form>
```

---

# 🔥 超重要ポイント

---

## ① Modelの役割

```text
Controller → HTMLへデータを渡す
```

---

## ② nameと引数の一致

```text
name="title" → String title
```

---

## ③ th:valueの意味

```text
既存データを表示する
```

---

## ④ newとupdateの違い

```text
Create → new Node()
Update → findById()
```

---

## ⑤ saveの本質

```text
新規でも更新でも save を使う
```

---

# 🔗 処理のつながり（最重要）

```text
HTML(edit)
↓
Controller(update)
↓
Service(update)
↓
Repository(save)
↓
DB

↓

DB
↓
Repository
↓
Service
↓
Controller
↓
HTML(list)
```

---
