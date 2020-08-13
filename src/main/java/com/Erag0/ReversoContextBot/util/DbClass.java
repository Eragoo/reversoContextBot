package com.Erag0.ReversoContextBot.util;

public class DbClass {
    private static DbClass dbClass = new DbClass();

    private DbClass() { }

    public static DbClass getInstance() {
        return dbClass;
    }

    public void AddUser(long chat_id, String username, String command, String language) {

    }

    public void UpdateLang(long chat_id, String username, String language) {

    }

    public String RestoreLang(long chat_id) {
       return null;
    }

    public Long Count(long chat_id) {
        return null;
    }


}
