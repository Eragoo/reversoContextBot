package com.Erag0.ReversoContextBot.error;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramException;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
public class TelegramExceptionHandler implements ExceptionHandler {
    @Override
    public void onException(TelegramException e) {
        log.log(Level.SEVERE, e.getMessage());
    }
}
