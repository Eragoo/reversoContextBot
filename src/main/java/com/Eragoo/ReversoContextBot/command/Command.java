package com.Eragoo.ReversoContextBot.command;

import com.Eragoo.ReversoContextBot.Message;
import com.pengrad.telegrambot.model.Update;

public interface Command {
    Message execute(Update update);
}
