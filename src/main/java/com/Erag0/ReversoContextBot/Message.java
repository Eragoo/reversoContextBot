package com.Erag0.ReversoContextBot;

import com.pengrad.telegrambot.model.request.Keyboard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Message {
    private final long chatId;
    private final String message;
    private final boolean hasKeyboard;
    private final Keyboard keyboard;

    public Message(long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
        this.hasKeyboard = false;
        this.keyboard = null;
    }

    public Message(long chatId, String message, Keyboard keyboard) {
        this.chatId = chatId;
        this.message = message;
        this.hasKeyboard = true;
        this.keyboard = keyboard;
    }
}
