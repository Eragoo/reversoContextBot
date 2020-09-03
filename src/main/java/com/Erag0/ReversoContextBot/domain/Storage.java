package com.Erag0.ReversoContextBot.domain;

public class Storage {
    private UserRepository userRepository;

    public Storage(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getLanguage(long chatId) {
        return userRepository.getLanguage(chatId);
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
