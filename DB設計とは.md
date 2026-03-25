# DB設計

## 前提知識
  
  - データ
    - 保存したい情報そのもの
  
  - テーブル
    - 同じ種類のデータをまとめる箱
  
  - カラム
    - テーブルの項目

  - レコード
    - 1件分のデータ
   
  - CRUD
    - Creat:作る
    - Read:見る
    - Update:更新する
    - Delete:削除する
   
  - エンティティ
    - DBに保存する対象となるもの
    - 例）User,Node,Quiz
   
  - リレーション
    - データ同士の繋がり
    - 例）1つの分野に複数のノードがある
  
  - 主キー
    - データを一意に区別する
    - 例）node.id = 1, node.id = 2

  - 外部キー
    - 別のテーブルとの繋がりを表す項目
    - 例）node.subject_id
  
## テーブル設計とは

- どんな種類の情報を、どんな項目で保存するかを決めること

- 例）Nodeテーブルを作る場合
  - id
  - title
  - content
  - subject_id
  - parent_node_id

## クラス設計とは

- プログラムの中でどんな役割の部品を作るかを決めること

1. データを表すクラス  
  例）
    - User
    - Subject
    - Node
    - Quiz
    - Review History

2. 処理を担当するクラス  
  例）
    - NodeService
    - QuizService
    - ReviwService
   

## API設計とは

- 画面からのリクエストを、サーバーにどう渡すかを決めること

  例えば、
  - ノード一覧を表示したい
  - ノードを新規登録したい
  - クイズ結果を保存したい
 
  こういう要求をどんな入り口で受けるかを決める

  入り口の例としては、

  - GET/nodes  
    ノード一覧を取得する
  - GET/nodes/{id}  
    ノード詳細を取得する
  - POST/nodes  
    ノードを新規作成する
  - POST/reviews/{quizId}/answer  
    クイズ回答結果を保存する
  

