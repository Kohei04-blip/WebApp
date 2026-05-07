# 📘 ノード一覧機能（Read）まとめ

---

# 🎯 全体の流れ（超重要）

```text
① ブラウザで /nodes にアクセス
↓
② ControllerのfindAll()が呼ばれる
↓
③ ServiceのfindAll()を呼ぶ
↓
④ RepositoryでDBから全件取得
↓
⑤ Serviceに返る
↓
⑥ Controllerに返る
↓
⑦ Modelにデータを入れる
↓
⑧ list.htmlに渡す
↓
⑨ 画面に一覧表示
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
  └ list.html ← 一覧画面
```

---

# 🧠 各ファイルの役割

---

## 🟦 Controller（NodeController）

```java
@GetMapping
public String findAll(Model model){
    List<Node> nodes = nodeService.findAll();
    model.addAttribute("nodes", nodes);
    return "nodes/list";
}
```

---

### 役割

```text
① URLを受け取る
② Serviceに依頼
③ データをModelに入れる
④ HTMLを返す
```

---

## 🟩 Service（NodeService）

```java
public List<Node> findAll() {
    return nodeRepository.findAll();
}
```

---

### 役割

```text
① データ取得処理を書く
② Repositoryを呼ぶ
③ Controllerへ返す
```

---

## 🟨 Repository

```java
nodeRepository.findAll();
```

---

### 役割

```text
DBから全件取得する
```

---

## 🟥 Entity（Node）

```java
private String title;
private String content;
```

---

### 役割

```text
DBの1レコードを表す
```

---

# 🧠 HTML（list.html）

```html
<ul>
    <li th:each="node : ${nodes}">
        <span th:text="${node.title}"></span>
        ：
        <span th:text="${node.content}"></span>
    </li>
</ul>
```

---

## 役割

```text
Modelのデータを画面に表示
```

---

# 🔥 超重要ポイント

---

## ① Modelの役割

```text
Controller → HTMLへデータを渡す箱
```

---

## ② th:each

```text
Listを1件ずつ取り出す
```

---

## ③ th:text

```text
値を画面に表示する
```

---

## ④ List<Node>

```text
複数データを扱うための型
```

---

# 🔗 処理のつながり（最重要）

```text
ブラウザ
↓
Controller(findAll)
↓
Service(findAll)
↓
Repository(findAll)
↓
DB

↓（戻る）

DB
↓
Repository
↓
Service
↓
Controller
↓
Model
↓
HTML(list)
↓
画面表示
```

---

# 🎯 一番重要な理解

```text
Controllerは「画面」
Serviceは「処理」
Repositoryは「DB」
```

---

# 🧠 Createとの違い（比較）

```text
一覧（Read） → データを取得
登録（Create） → データを保存
```

