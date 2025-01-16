package com.example.tgFedya.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SpeechKitService {

    @Value("${yandex.speechkit.api-key}") // API-ключ из application.properties
    private String apiKey;

    @Value("${yandex.speechkit.folder-id}") // Folder ID из application.properties
    private String folderId;

    private final WebClient webClient;

    public SpeechKitService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://stt.api.cloud.yandex.net").build();
    }

    public String transcribeVoice(byte[] audioData) {
        // Логика отправки запроса к Yandex SpeechKit API
        return "Расшифрованный текст";
    }
}