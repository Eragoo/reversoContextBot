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
        String lang = dbClass.RestoreLang(chat_id);
        return lang;
    }

    public void StoreLang(String lang) {
        long chat_id = update.callbackQuery().from().id();
        String username = update.callbackQuery().from().username();

        if (isSet(chat_id)) {
            System.out.println("Trying update user!");
            dbClass.UpdateLang(chat_id, username, lang);
            System.out.println("User updated!");

        } else {
            String command = "Undefined";
            System.out.println("Trying add user!");
            dbClass.AddUser(chat_id, username, command, lang);
            System.out.println("User added!");
        }
    }

    public void StoreCommand(String command){
        long chat_id = update.message().chat().id();
        String username = update.message().chat().username();
        String lang = "Undefined";
        if (isSet(chat_id)) {
            System.out.println("Trying update user!");
            dbClass.UpdateCommand(chat_id, username, command, lang);
            System.out.println("User updated!");

        } else {
            System.out.println("Trying add user!");
            dbClass.AddUser(chat_id, username, command, lang);
            System.out.println("User added!");
        }
    }

    public String RestoreCommand() {
        long chat_id = update.message().chat().id();
        String command = dbClass.RestoreCommand(chat_id);
        return command;
    }

    public boolean isSet(long chat_id){
        System.out.println("Trying to calcualte count of users with this chat_id!");
        long i = dbClass.Count(chat_id);
        System.out.println("Count of records with that chat_id = " + i);
        if (i != 0) {
            return true;
        }
        return false;
    }
}
