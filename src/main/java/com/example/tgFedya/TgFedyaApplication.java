package com.example.tgFedya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.example.tgFedya.bot.TgFedyaBot;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class TgFedyaApplication {

    private final TgFedyaBot tgFedyaBot;

    public TgFedyaApplication(TgFedyaBot tgFedyaBot) {
        this.tgFedyaBot = tgFedyaBot;
    }

    public static void main(String[] args) {
        SpringApplication.run(TgFedyaApplication.class, args);
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(tgFedyaBot); // Используем бин, созданный Spring

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}