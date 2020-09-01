package com.Erag0.ReversoContextBot.bot.command;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    void execute(Update update);
}
