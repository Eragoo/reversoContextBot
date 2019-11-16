package com.Erag0.ReversoContextBot.TelegramBotCore;


import com.Erag0.ReversoContextBot.Util.GetCommandFromMessage;
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
                if (GetCommandFromMessage.isCommand(update.message().text())){
                    commandController.executeCommand(GetCommandFromMessage.getCommand());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}