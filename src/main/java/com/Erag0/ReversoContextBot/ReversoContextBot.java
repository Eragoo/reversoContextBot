package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.bot.callback.CallbackQueryLanguageHandler;
import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.CommandParser;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class ReversoContextBot {
    private static final String TOKEN = "592148368:AAE59ohkGYvBjkJLt-zrmmtPKYANRtSrTYY";
    private TelegramBot bot;
    private Storage storage;
    private BotMessageSender messageSender;

    public ReversoContextBot(Storage storage) {
        this.storage = storage;
        this.bot = new TelegramBot(TOKEN);
        this.messageSender = new BotMessageSender(bot);
    }

    public void run() {
        CallbackQueryLanguageHandler handler = new CallbackQueryLanguageHandler(messageSender, storage);
        this.bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message() != null) {
                    String messageText = update.message().text();
                    Command command = CommandParser.getCommand(messageText, storage, messageSender);
                    command.execute(update);
                } else if (update.callbackQuery() != null) {
                    handler.handle(update.callbackQuery());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}