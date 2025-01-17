package com.example.tgFedya.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TgFedyaBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;

    public TgFedyaBot(@Value("${telegram.bot.token}") String botToken,
                      @Value("${telegram.bot.username}") String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    private static final Logger logger = LoggerFactory.getLogger(TgFedyaBot.class);

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
            logger.info("Got message: {}", messageText);

            switch (messageText) {
                case "/start":
                    sendMessage(chatId, "Привет! Я Кибер Федяра. Чем могу помочь?");
                    logger.info("Отправлено сообщение /start в чат {}", chatId);
                    break;
                case "/help":
                    sendMessage(chatId, """
                            Доступные команды:
                            /start - Приветственное сообщение
                            /help - Список команд
                            /save_attachment - Сохранить вложение
                            /store_message - Сохранить сообщение
                            /list_members - Список участников
                            /transcribe_voice - Расшифровать голосовое сообщение""");
                    logger.info("Отправлено сообщение /help в чат {}", chatId);
                    break;
                // Другие команды
                default:
                    sendMessage(chatId, "Этой команды я не знаю, либо она не работает, дебилище!");
                    logger.info("Отправлена заглушка в чат {}", chatId);
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
            logger.error(e.getMessage());
        }
    }
}