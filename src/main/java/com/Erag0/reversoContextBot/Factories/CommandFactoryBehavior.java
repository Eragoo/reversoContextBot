package com.Erag0.reversoContextBot.Factories;

import com.Erag0.reversoContextBot.telegram.Commands.Command;
import com.Erag0.reversoContextBot.telegram.Commands.EmptyCommand;
import com.Erag0.reversoContextBot.telegram.Commands.HelpCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandFactoryBehavior {
    private TelegramBot bot;
    private Update update;

    public CommandFactoryBehavior(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
    }

    public Command getCommand(String command) {
        if ("/help".equals(command)) {
            return new HelpCommand(bot, update);
        }
        return new EmptyCommand(bot, update);
    }
}
