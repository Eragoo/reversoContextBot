package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.Message;
import com.Erag0.ReversoContextBot.Language;
import com.Erag0.ReversoContextBot.db.Storage;
import com.Erag0.ReversoContextBot.db.UserAction;
import com.pengrad.telegrambot.model.Update;

public class SetLanguageCommand implements Command {
    public static final CommandName NAME = CommandName.SET_LANGUAGE;

    private final Storage storage;

    public SetLanguageCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Message execute(Update update) {
        String messageText = "*Язык перевода задан*✨\n";

        long chatId = update.callbackQuery().from().id();
        String username = update.callbackQuery().from().username();
        String lang = update.callbackQuery().data();

        if (Language.isSupported(lang)) {
            UserAction userAction = UserAction.builder()
                    .chatId(chatId)
                    .lang(lang)
                    .username(username)
                    .build();
            storage.saveUser(userAction);
        } else {
            messageText = "*Язык не поддерживается*😔\n";
        }
        return new Message(chatId, messageText);
    }
}
