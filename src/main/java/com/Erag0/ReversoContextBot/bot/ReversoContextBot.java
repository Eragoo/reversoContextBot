package com.Erag0.ReversoContextBot.bot;

import com.Erag0.ReversoContextBot.bot.callback.CallbackQueryLanguageHandler;
import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.CommandParser;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.java.Log;
import sun.util.logging.PlatformLogger;


import java.util.logging.Level;
import java.util.logging.Logger;

import static com.Erag0.ReversoContextBot.bot.BotProperties.TOKEN;

@Log
public class ReversoContextBot {
    private TelegramBot bot;
    private Storage storage;
    private BotMessageSender messageSender;

    public ReversoContextBot(Storage storage) {
        this.storage = storage;
        this.bot = new TelegramBot(TOKEN);
        this.messageSender = new BotMessageSender(bot);
    }

    public void run() {
        log.info("Application started!");
        CallbackQueryLanguageHandler callbackQueryHandler = new CallbackQueryLanguageHandler(messageSender, storage);
        this.bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    if (isCommandReceived(update)) {
                        String messageText = update.message().text();
                        Command command = CommandParser.getCommand(messageText, storage, messageSender);
                        command.execute(update);
                    } else if (isCallbackQueryReceived(update)) {
                        callbackQueryHandler.handle(update.callbackQuery());
                    }
                } catch (Exception e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private boolean isCallbackQueryReceived(Update update) {
        return update.callbackQuery() != null;
    }

    private boolean isCommandReceived(Update update) {
        return update.message() != null;
    }
}