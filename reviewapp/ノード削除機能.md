# 📘 削除機能（Delete）まとめ

# 🎯 全体の流れ

```text
① 一覧画面で「削除」ボタンを押す
↓
② POST /nodes/{id}/delete が送信される
↓
③ Controllerがidを受け取る
↓
④ Serviceに削除処理を依頼する
↓
⑤ RepositoryがDBから削除する
↓
⑥ redirect:/nodes で一覧画面に戻る
↓
⑦ 削除後の一覧が表示される
```

---

# 🧠 各ファイルの役割

## 1. list.html

```html
<form th:action="@{/nodes/{id}/delete(id=${node.id})}" method="post">
    <button type="submit">削除</button>
</form>
```

役割：

```text
削除したいNodeのidをURLに入れてPOST送信する
```

---

## 2. NodeController.java

```java
@PostMapping("/{id}/delete")
public String delete(@PathVariable Long id) {
    nodeService.delete(id);
    return "redirect:/nodes";
}
```

役割：

```text
URLからidを受け取る
Serviceに削除を依頼する
一覧画面に戻す
```

---

## 3. NodeService.java

```java
public void delete(Long id) {
    nodeRepository.deleteById(id);
}
```

役割：

```text
Repositoryを使って削除処理を行う
```

---

## 4. NodeRepository.java

```java
nodeRepository.deleteById(id);
```

役割：

```text
指定されたidのデータをDBから削除する
```

---

# 🔥 重要ポイント

## ① 削除はGETではなくPOST

```text
削除はDBのデータを変更する処理
だからGETではなくPOSTを使う
```

---

## ② idで削除対象を特定する

```text
/nodes/1/delete
```

この場合、

```text
id = 1
```

のNodeを削除します。

---

## ③ voidなのでreturnしない

```java
public void delete(Long id) {
    nodeRepository.deleteById(id);
}
```

`deleteById()` は値を返さない処理なので、`return` は書きません。

---

# 🔗 処理のつながり

```text
list.html
↓
NodeController.delete()
↓
NodeService.delete()
↓
NodeRepository.deleteById()
↓
DBから削除
↓
redirect:/nodes
↓
一覧再表示
```

---

# 🎯 CRUDの完成

```text
Create：新規登録
Read：一覧表示
Update：編集
Delete：削除
```

これでCRUDの基本が完成です。
