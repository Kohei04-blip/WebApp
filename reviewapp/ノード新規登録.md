# 📘 ノード新規登録（Create）まとめ

---

# 🎯 全体の流れ（超重要）

```text
① /nodes/new にアクセス
↓
② 入力画面（new.html）表示
↓
③ フォーム入力 → 送信（POST /nodes）
↓
④ Controllerが受け取る
↓
⑤ Serviceで処理（Node作成）
↓
⑥ RepositoryでDB保存
↓
⑦ redirect:/nodes
↓
⑧ 一覧画面（list.html）表示
```

---

# 🧠 ディレクトリ構成と役割

```text
src/main/java/com/example/reviewapp/
├── controller
│   └── NodeController.java   ← 入口（リクエスト受け取り）
├── service
│   └── NodeService.java      ← 処理（ロジック）
├── repository
│   └── NodeRepository.java   ← DB操作
├── entity
│   └── Node.java             ← データの箱

src/main/resources/
├── templates
│   └── nodes
│       ├── list.html         ← 一覧画面
│       └── new.html          ← 登録画面
```

---

# 🧠 各クラスの役割と動き

---

## 🟦 Controller（NodeController）

```java
@GetMapping("/new")
public String newForm(){
    return "nodes/new";
}
```

👉 登録画面を表示

---

```java
@PostMapping
public String create(String title, String content){
    nodeService.create(title, content);
    return "redirect:/nodes";
}
```

👉 入力データを受け取る
👉 Serviceに渡す
👉 一覧にリダイレクト

---

## 🟩 Service（NodeService）

```java
public void create(String title, String content) {
    Node node = new Node();

    node.setTitle(title);
    node.setContent(content);

    nodeRepository.save(node);
}
```

👉 データを加工・作成
👉 Repositoryに渡す

---

## 🟨 Repository（NodeRepository）

```java
nodeRepository.save(node);
```

👉 DBに保存する

---

## 🟥 Entity（Node）

```java
private String title;
private String content;
```

👉 DBの1レコードを表す

---

# 🧠 HTMLの役割

---

## new.html（入力）

```html
<form action="/nodes" method="post">
    <input name="title">
    <input name="content">
</form>
```

👉 nameとControllerが紐づく

```text
name="title" → String title
name="content" → String content
```

---

## list.html（表示）

```html
<li th:each="node : ${nodes}">
    <span th:text="${node.title}"></span>
</li>
```

👉 Modelからデータ受け取り

---

# 🔗 全体の関連性（超重要）

```text
HTML(name)
↓
Controller(引数)
↓
Service(処理)
↓
Repository(DB保存)
↓
DB

↓（戻り）

DB
↓
Repository
↓
Service
↓
Controller(Model)
↓
HTML表示
```

---

# 🔥 一番重要な理解

---

## MVCの役割

```text
Controller：受付・画面との橋渡し
Service：処理を書く場所
Repository：DB操作
Entity：データの箱
```

---

# 🧠 今回のキーポイント

---

## ① nameと引数の一致

```text
name="title" → String title
```

---

## ② redirectの意味

```text
redirect:/nodes = GET /nodes を再実行
```

---

## ③ なぜServiceでNodeを作るか

```text
役割分担（Controllerは処理を書かない）
```

---
