package com.Eragoo.ReversoContextBot.command;

import com.Eragoo.ReversoContextBot.Message;
import com.Eragoo.ReversoContextBot.Language;
import com.Eragoo.ReversoContextBot.db.UserAction;
import com.Eragoo.ReversoContextBot.db.UserRepository;
import com.pengrad.telegrambot.model.Update;

public class SetLanguageCommand implements Command {
    public static final CommandName NAME = CommandName.SET_LANGUAGE;

    private final UserRepository repository;

    public SetLanguageCommand(UserRepository repository) {
        this.repository = repository;
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
            repository.saveUser(userAction);
        } else {
            messageText = "*Язык не поддерживается*😔\n";
        }
        return new Message(chatId, messageText);
    }
}
