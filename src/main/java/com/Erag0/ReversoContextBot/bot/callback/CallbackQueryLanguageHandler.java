package com.Erag0.ReversoContextBot.bot.callback;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.db.UserAction;
import com.Erag0.ReversoContextBot.db.Storage;
import com.pengrad.telegrambot.model.CallbackQuery;

import java.util.Arrays;

public class CallbackQueryLanguageHandler {
    private Storage storage;
    private BotMessageSender messageSender;

    public CallbackQueryLanguageHandler(BotMessageSender messageSender, Storage storage) {
        this.storage = storage;
        this.messageSender = messageSender;

    }

    public void handle(CallbackQuery callbackQuery) {
        String messageText = "*Язык перевода задан*✨\n";

        long chatId = callbackQuery.from().id();
        String username = callbackQuery.from().username();
        String callbackData = callbackQuery.data();

        if (isLanguageSupported(callbackData)) {
            UserAction userAction = UserAction.builder()
                    .chatId(chatId)
                    .lang(callbackData)
                    .username(username)
                    .build();
            storage.saveUser(userAction);
        } else {
            messageText = "*Язык не поддерживается*😔\n";
        }
        messageSender.sendMessage(chatId, messageText);
    }

    private boolean isLanguageSupported(String lang) {
        return Arrays.stream(Language.values())
                .map(Language::getFullName)
                .anyMatch(name -> name.equals(lang));
    }
}
