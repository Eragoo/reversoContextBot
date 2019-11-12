package com.Erag0.ReversoContextBot.TelegramBotCore;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class ReversoContextBot {
    private static final String TOKEN = "";
    private TelegramBot bot;

    public ReversoContextBot() {
        this.bot = new TelegramBot(TOKEN);
    }

    public void Start() {
        BotCore();
    }

    private void BotCore() {
        this.bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                CommandController commandController = new CommandController(bot, update);
                commandController.executeCommand("/start");
                commandController.executeCommand("/help");
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

}