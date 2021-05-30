package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.Message;
import com.pengrad.telegrambot.model.Update;

public interface Command {
    Message execute(Update update);
}
