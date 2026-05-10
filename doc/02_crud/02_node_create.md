# 🎯 問題
新規登録機能を0から作る場合、以下の問いに答えよ
  
① どのクラス（ファイル）を作るか？  
② Controllerにはどんなメソッドを書くか？  
③ Serviceにはどんなメソッドを書くか？  
④ Repositoryはどう定義するか？  
⑤ HTMLではどのようにデータを表示するか？  

---

# 🧠 自分の回答

① 作るファイル  
- new.html  
② Controller  
/newにアクセスされたら、nodeService.create()で新しいtitleとcontentを作成し、nodes/new.listに表示する。  
③ nodeRepository.create()を呼び、DB
④ わからない
⑤ わからない

---

# ✅ 正解・フィードバック

① どのクラス（ファイル）を作るか？  
```
Node.java
NodeRepository.java
NodeService.java
NodeController.java
new.html
list.html
```

② Controllerにはどんなメソッドを書くか？  

- 入力画面を表示するメソッド
```java
@GetMapping("/new")
public String newForm(Model model) {
    model.addAttribute("nodeForm", new NodeForm());
    return "nodes/new";
}
```

- 登録処理をするメソッド
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

③ Serviceにはどんなメソッドを書くか？  
```java
public void create(String title, String content) {
    Node node = new Node();

    node.setTitle(title);
    node.setContent(content);

    nodeRepository.save(node);
}
```

④ Repositoryはどう定義するか？  
```java
public interface NodeRepository extends JpaRepository<Node, Long> {
}
```

⑤ HTMLではどのようにデータを表示するか？  

```html
<form th:action="@{/nodes}" th:object="${nodeForm}" method="post">
    <input type="text" th:field="*{title}">
    <div th:if="${#fields.hasErrors('title')}" th:errors="*{title}"></div>

    <input type="text" th:field="*{content}">

    <button type="submit">登録</button>
</form>
```
