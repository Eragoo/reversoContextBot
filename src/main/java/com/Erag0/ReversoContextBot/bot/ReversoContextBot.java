package com.Erag0.ReversoContextBot.bot;

import com.Erag0.ReversoContextBot.bot.callback.CallbackQueryLanguageHandler;
import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.CommandParser;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.Erag0.ReversoContextBot.error.TelegramExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.java.Log;

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
        TelegramExceptionHandler exceptionHandler = new TelegramExceptionHandler();
        UpdatesListener updatesListener = getUpdatesListener();
        bot.setUpdatesListener(updatesListener, exceptionHandler);
    }

    private UpdatesListener getUpdatesListener() {
        CallbackQueryLanguageHandler callbackQueryHandler = new CallbackQueryLanguageHandler(messageSender, storage);
        return updates -> {
            for (Update update : updates) {
                processSingleUpdate(callbackQueryHandler, update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }

    private void processSingleUpdate(CallbackQueryLanguageHandler callbackQueryHandler, Update update) {
        if (isCommandReceived(update)) {
            String messageText = update.message().text();
            Command command = CommandParser.getCommand(messageText, storage, messageSender);
            command.execute(update);
        } else if (isCallbackQueryReceived(update)) {
            callbackQueryHandler.handle(update.callbackQuery());
        }
    }

    private boolean isCallbackQueryReceived(Update update) {
        return update.callbackQuery() != null;
    }

    private boolean isCommandReceived(Update update) {
        return update.message() != null;
    }
}