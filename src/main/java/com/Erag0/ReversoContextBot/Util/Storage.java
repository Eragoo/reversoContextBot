package com.Erag0.ReversoContextBot.Util;

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
            dbClass.UpdateLang(chat_id, username, lang);
        } else {
            String command = "Undefined";
            dbClass.AddUser(chat_id, username, command, lang);
        }
    }

    public void StoreCommand(String command){
        long chat_id = update.message().chat().id();
        String username = update.message().chat().username();
        String lang = "Undefined";
        if (isSet(chat_id)) {
            dbClass.UpdateCommand(chat_id, username, command, lang);
        } else {
            dbClass.AddUser(chat_id, username, command, lang);
        }
    }

    public String RestoreCommand() {
        long chat_id = update.message().chat().id();
        return dbClass.RestoreCommand(chat_id);
    }

    public boolean isSet(long chat_id){
        long i = dbClass.Count(chat_id);
        if (i != 0) {
            return true;
        }
        return false;
    }
}
