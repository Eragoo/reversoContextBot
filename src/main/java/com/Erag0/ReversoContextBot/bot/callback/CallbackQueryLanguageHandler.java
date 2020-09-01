package com.Erag0.ReversoContextBot.bot.callback;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.User;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.CallbackQuery;

public class CallbackQueryLanguageHandler {
    private Storage storage;
    private BotMessageSender messageSender;

    public CallbackQueryLanguageHandler(BotMessageSender messageSender, Storage storage) {
        this.storage = storage;
        this.messageSender = messageSender;

    }

    public void handle(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.from().id();
        String username = callbackQuery.from().username();
        String lang = callbackQuery.data();//написать парсер который парсит по енаму языков доступных
        User user = User.builder()
                .chatId(chatId)
                .language(lang)
                .username(username)
                .build();

        storage.saveUser(user);
        sendSuccessMessage(user.getChatId());
    }
    private void sendSuccessMessage(long chatId) {
        String messageText = "*Язык перевода задан*✨\n";
        messageSender.sendMessage(chatId, messageText);
    }
}
