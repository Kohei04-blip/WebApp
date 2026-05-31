package com.example.reviewapp.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {

    public String correctContent(String content) {

        if (content == null || content.isBlank()) {
            return content;
        }

        if (content.contains("List")) {
            return "Listは、複数のデータを順番に管理するためのJavaのコレクションです。";
        }

        if (content.contains("Controller")) {
            return "Controllerは、ブラウザからのリクエストを受け取り、Serviceへ処理を依頼し、画面へデータを渡す役割を持ちます。";
        }

        return content + "（内容をわかりやすく整理しました）";
    }
}