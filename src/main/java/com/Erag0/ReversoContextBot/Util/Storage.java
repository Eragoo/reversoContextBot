package com.Erag0.ReversoContextBot.Util;


import com.pengrad.telegrambot.model.Update;
import org.sqlite.core.DB;

import java.sql.SQLException;

public class Storage {
    private Update update;
    private DbClass dbClass;

    public Storage(Update update) {
        dbClass = DbClass.getInstance();
        this.update = update;
    }

    public void StoreCommand(String command){
        long chat_id = update.message().chat().id();
        String username = update.message().chat().username();
        String lang = "Undefined";
        System.out.println("Trying add user!");
        dbClass.AddUser(chat_id, username, command, lang);
        System.out.println("User added!");
    }

    public String RestoreCommand() {
        String chat_id = update.message().chat().id().toString();
        String command ="";
        return command;
    }


    public boolean isSet(String where){

        return false;
    }


}
