package com.example.tgFedya.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TgFedyaBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;

    public TgFedyaBot(@Value("${telegram.bot.token}") String botToken,
                      @Value("${telegram.bot.username}") String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    sendMessage(chatId, "Привет! Я Кибер Федяра. Чем могу помочь?");
                    break;
                case "/help":
                    sendMessage(chatId, "Доступные команды:\n" +
                            "/start - Приветственное сообщение\n" +
                            "/help - Список команд\n" +
                            "/save_attachment - Сохранить вложение\n" +
                            "/store_message - Сохранить сообщение\n" +
                            "/list_members - Список участников\n" +
                            "/transcribe_voice - Расшифровать голосовое сообщение");
                    break;
                // Другие команды
                default:
                    sendMessage(chatId, "Этой команды я не знаю, либо она не работает, дебилище!");
                    break;
            }
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}