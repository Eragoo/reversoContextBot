package com.Erag0.ReversoContextBot.bot.callbackHandler;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.User;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.model.Update;

public class LangCallbackHandler implements Handler{
    private Storage storage;
    private Update update;
    private BotMessageSender messageSender;

    public LangCallbackHandler(BotMessageSender messageSender, Update update, Storage storage) {
        this.storage = storage;
        this.update = update;
        this.messageSender = messageSender;

    }

    public void execute(String query) {
        User user = User.builder()
                .chatId(update.message().chat().id())
                .language(query)
                .username(update.message().chat().username())
                .build();

        storage.saveUser(user);
        sendSuccessMessage(user.getChatId());
    }
    private void sendSuccessMessage(long chatId) {
        String messageText = "*Язык перевода задан*✨\n";
        messageSender.sendMessage(chatId, messageText);
    }
}
