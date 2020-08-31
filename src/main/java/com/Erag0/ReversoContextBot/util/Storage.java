package com.Erag0.ReversoContextBot.util;

import com.Erag0.ReversoContextBot.domain.User;
import com.Erag0.ReversoContextBot.domain.UserRepository;
import com.pengrad.telegrambot.model.Update;

public class Storage {
    private Update update;
    private UserRepository userRepository;

    public Storage(Update update, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.update = update;
    }

    public String RestoreLang() {
        long chat_id = update.message().chat().id();
        return userRepository.getLang(chat_id);
    }

    public void StoreLang(String lang) {
        long chat_id = update.callbackQuery().from().id();
        String username = update.callbackQuery().from().username();
        if (isSet(chat_id)) {
            userRepository.updateLang(chat_id, lang);
        } else {
            String command = "Undefined";
            User user = User.builder()
                    .chat_id(chat_id)
                    .username(username)
                    .command(command)
                    .language(lang)
                    .build();
            userRepository.save(user);
        }
    }

    public boolean isSet(long chat_id) {
        long i = userRepository.count(chat_id);
        return i != 0;
    }
}
