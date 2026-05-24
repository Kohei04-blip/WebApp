# 📘 Spring Boot 機能追加の思考手順

# 🎯 まず最初に考えること

```text id="3v0k0g"
「画面で何をしたいか？」
```

ここから始める。

---

# 例

```text id="10tfwz"
カテゴリ検索したい
```

---

# 🧠 すると考えること

```text id="6qk2wa"
何を入力する？
何を表示する？
```

---

# 📘 STEP1：HTMLを考える

# 🎯 考えること

```text id="vjlwma"
何を入力する？
```

---

# 例

---

## 自由入力

```html id="jlwmb"
<input>
```

例：

```text id="jlwmc"
キーワード
タイトル
```

---

## 選択

```html id="jlwmd"
<select>
```

例：

```text id="jlwme"
カテゴリ
```

---

## 送信

```html id="jlwmf"
<button>
```

---

# 🎯 次に考える

```text id="jlwmg"
何をControllerへ送る？
```

---

# 例

```html id="jlwmh"
name="keyword"
name="categoryId"
```

---

# 📘 STEP2：Controllerを考える

# 🎯 Controllerの役割

```text id="jlwmi"
受け取る
↓
Serviceへ渡す
↓
HTMLへ返す
```

---

# 🎯 考えること

```text id="jlwmj"
何を受け取る？
```

---

# 例

```java id="jlwmk"
String keyword
Long categoryId
```

---

# 🎯 次に考える

```text id="jlwml"
HTMLへ何を返す？
```

---

# 例

```java id="jlwmm"
model.addAttribute(...)
```

---

# 📘 STEP3：Serviceを考える

# 🎯 Serviceの役割

```text id="jlwmn"
処理の整理
if分岐
```

---

# 🎯 考えること

```text id="jlwmo"
どう処理する？
```

---

# 例

```text id="jlwmp"
keywordあり？
categoryあり？
```

---

# 🎯 Serviceは司令塔

```text id="jlwmq"
どのRepositoryを使う？
```

を決める。

---

# 📘 STEP4：Repositoryを考える

# 🎯 Repositoryの役割

```text id="jlwmr"
DB操作専用
```

---

# 🎯 考えること

```text id="jlwms"
何条件で検索する？
```

---

# 例

```java id="jlwmt"
findByCategory_Id()

findByTitleContaining()

findByTitleContainingAndCategory_Id()
```

---

# 📘 STEP5：Entityを考える

# 🎯 Entityの役割

```text id="jlwmu"
DBの形
```

---

# 🎯 考えること

```text id="jlwmv"
新しい項目必要？
```

---

# 例

```text id="jlwmw"
reviewDate
createdAt
user
```

---

# 📘 最終的な思考順

# 🎯 基本形

```text id="jlwmx"
① 画面で何したい？
↓
② 何を入力する？
↓
③ Controllerで何を受け取る？
↓
④ Serviceでどう処理する？
↓
⑤ Repositoryで何検索する？
↓
⑥ Entityに何が必要？
```

---

# 📘 MVCで整理すると

---

# View（HTML）

```text id="jlwmy"
入力
表示
```

---

# Controller

```text id="jlwmz"
受け取り
受け渡し
```

---

# Service

```text id="jlwn0"
処理
条件分岐
```

---

# Repository

```text id="jlwn1"
DB操作
```

---

# Entity

```text id="jlwn2"
DBの形
```

---

# 🔥 超重要

# Controllerを書く前に考える

```text id="jlwn3"
「画面で何したい？」
```

これがかなり大事。

---

# 🚀 今のあなたが伸びている部分

特に良いのは👇。

```text id="jlwn4"
「責任で分ける」
```

を考え始めていること。

これは、

```text id="jlwn5"
MVC
オブジェクト指向
実務設計
```

全部につながります 👍
