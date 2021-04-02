package com.Eragoo.reversoContextBot;

import org.bson.types.ObjectId;

import java.net.UnknownHostException;

public class App {
    public static void main(String[] args) throws UnknownHostException {
        MessageRepository r = new MessageRepository();
        Message saved = r.save(Message.builder().telegramId(1L).text("Texewqeqt").build());
        r.delete(saved);
    }
}
