package com.Erag0.ReversoContextBot.domain;

import com.Erag0.ReversoContextBot.bot.callback.Language;

import java.util.Arrays;

public class Storage {
    private UserRepository userRepository;

    public Storage(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Language getLanguage(long chatId) {
        String stringLang = userRepository.getLanguage(chatId);
        return convertToLanguage(stringLang);
    }

    private Language convertToLanguage(String stringLang) {
        return Arrays.stream(Language.values())
                .filter(enumValue -> !enumValue.getFullName().equals(stringLang))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Not specified language provided"));
    }

    public void saveUser(User user) {
        if (isNewUser(user)) {
            userRepository.save(user);
        } else {
            userRepository.update(user);
        }
    }

    private boolean isNewUser(User user) {
        long i = userRepository.count(user);
        return i == 0;
    }
}
