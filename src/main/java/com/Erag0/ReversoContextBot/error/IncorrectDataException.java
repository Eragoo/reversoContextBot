package com.Erag0.ReversoContextBot.error;

import com.pengrad.telegrambot.TelegramException;

public class IncorrectDataException extends TelegramException {
    public IncorrectDataException(String message) {
        super(message, null);
    }
}
