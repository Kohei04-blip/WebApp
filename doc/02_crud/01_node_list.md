
# 🎯 問題
ノード管理アプリを0から作るとする。  
まずはじめに、一覧画面を作るとする。
  
① どのクラス（ファイル）を作るか？  
② Controllerにはどんなメソッドを書くか？  
③ Serviceにはどんなメソッドを書くか？  
④ Repositoryはどう定義するか？  
⑤ HTMLではどのようにデータを表示するか？  

---

# 🧠 自分の回答

① Nodeクラス、NodeServiceクラス、NodeControllerクラス、NodeRepositoryクラス  
② findAllメソッド  
③ findAllメソッド  
④ わからない  
⑤ わからない  

---

# ✅ 正解・フィードバック
① 作るファイル
- Node.java
- NodeRepository.java
- NodeService.java
- NodeController.java
- templates/nodes/list.html

② Controller
/nodes にアクセスされたら、nodeService.findAll()で一覧を取得し、Modelに "nodes" という名前で渡して、nodes/list.htmlを表示する。

③ Service
nodeRepository.findAll() を呼び、DBから取得したNode一覧をControllerへ返す。

④ Repository
JpaRepository<Node, Long> を継承したinterfaceを作る。
```java
public interface NodeRepository extends JpaRepository<Node, Long> {
}
```

⑤ HTML
th:eachで${nodes}を1件ずつ取り出し、th:textでtitleやcontentを表示する。

```java
<li th:each="node : ${nodes}">
    <span th:text="${node.title}"></span>
</li>
```
