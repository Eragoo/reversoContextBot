package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.bot.CommandController;
import com.Erag0.ReversoContextBot.bot.callbackHandler.LangCallbackHandler;
import com.Erag0.ReversoContextBot.util.GetCommandFromMessage;
import com.Erag0.ReversoContextBot.util.Storage;
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
        this.bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    CommandController commandController = new CommandController(bot, update);
                    LangCallbackHandler handler = new LangCallbackHandler(messageSender, update, storage);

                    if (update.message() != null) {
                        commandController.executeCommand(GetCommandFromMessage.getCommand(update.message().text()));
                    } else if (update.callbackQuery() != null) {
                        handler.execute(update.callbackQuery().data());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}