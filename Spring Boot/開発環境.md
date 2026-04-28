# 開発環境

## 全体像

```
1. プロジェクト作成
2. Javaバージョン決定
3. Gradle or Maven設定
4. DB設定
5. application.yml設定
6. Git初期化
7. パッケージ構成作成
```

### 1. プロジェクト作成

まずは、Spring Initializrでプロジェクトを作る。  
▶️`Spring Initializr とは、プロジェクト雛形を簡単に作ることができるサービス`

URL：  
https://start.spring.io/

選ぶ内容は以下の通り。
```
Project: Gradle - Groovy
Language: Java
Spring Boot: デフォルト
Group: com.example
Artifact: reviewapp
Name: reviewapp
Package name: com.example.reviewapp
Packaging: Jar
Java: 17
```
Dependeciesは以下の通り。
```
Spring Web
Thymeleaf
Spring Data JPA
H2 Database
```

---

### 2. Javaバージョン決定

最初は`Java 17`がおすすめ。  
理由は、Spring Boot 3系で安定して使いやすいから。

---

### 3. Gradle or Maven設定

今回はGradleでOK。  
Spring Initializrで`Gradle - Groovy`を選べば、基本設定は自動で作られる。  
作成後にこういうファイルができる。  
```
build.gradle
settings.gradle
```

Gradleとは？  
- ビルド・実行するツール
- アプリを動かす裏方

---

### 4. DB設定

最初は `H2 Database`でOK。  
理由は、MySQLより設定が簡単で、まず動かす練習に向いているから。  

---

### 5. application.yml設定

`src/main/resources/application.yml`を作る。  

```ymal
spring:
  datasource:
    url: jdbc:h2:mem:reviewapp
    driver-class-name: org.h2.Driver
    username: sa
    password:

  h2:
    console:
      enabled: true

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  thymeleaf:
    cache: false
```
- 設定ファイルのこと
- DBや環境設定を各場所

---

### 6. Git初期化

プロジェクトフォルダで実行する。   
```bash
git init
git add .
git commit -m "Initial commit"
```

手順  
1. プロジェクトフォルダに移動
```
cd reviewapp
```
2. 初期化
```
git init
```
3. ファイル追加
```
git add .
```
4. 保存（コミット）
```
git commit -m "初期設定"
```

---

### 7. パッケージ構成作成

この構成で作る。  
```
src/main/java/com/example/reviewapp/
├── ReviewappApplication.java
├── controller/
│   └── NodeController.java
├── service/
│   └── NodeService.java
├── repository/
│   └── NodeRepository.java
└── entity/
    └── Node.java
```
HTMLはここに入れる。
```
src/main/resources/templates/
└── nodes/
    └── list.html
```

---
