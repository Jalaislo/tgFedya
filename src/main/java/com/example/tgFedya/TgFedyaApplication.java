package com.example.tgFedya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.tgFedya.bot.TgFedyaBot;

@SpringBootApplication
public class TgFedyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgFedyaBot.class, args);
    }

}