package com.Erag0.ReversoContextBot.util;

import com.Erag0.ReversoContextBot.domain.DbClass;
import com.pengrad.telegrambot.model.Update;

public class Storage {
    private Update update;
    private DbClass dbClass;

    public Storage(Update update) {
        dbClass = DbClass.getInstance();
        this.update = update;
    }

    public String RestoreLang() {
        long chat_id = update.message().chat().id();
        return dbClass.RestoreLang(chat_id);
    }

    public void StoreLang(String lang) {
        long chat_id = update.callbackQuery().from().id();
        String username = update.callbackQuery().from().username();
        if (isSet(chat_id)) {
            dbClass.UpdateLang(chat_id, lang);
        } else {
            String command = "Undefined";
            dbClass.AddUser(chat_id, username, command, lang);
        }
    }

    public boolean isSet(long chat_id){
        long i = dbClass.Count(chat_id);
        return i != 0;
    }
}
