package com.Erag0.ReversoContextBot.db;

import java.util.Optional;

public class Storage {
    private final UserRepository userRepository;

    public Storage(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<String> getLanguage(long chatId) {
        return userRepository.getLanguage(chatId);
    }

    public void saveUser(UserAction userAction) {
        if (isNewUser(userAction)) {
            userRepository.save(userAction);
        } else {
            userRepository.update(userAction);
        }
    }

    private boolean isNewUser(UserAction userAction) {
        long i = userRepository.count(userAction);
        return i == 0;
    }
}
