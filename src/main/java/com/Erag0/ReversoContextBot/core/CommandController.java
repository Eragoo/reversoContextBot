package com.Erag0.ReversoContextBot.core;


import com.Erag0.ReversoContextBot.factories.CommandFactoryBehavior;
import com.Erag0.ReversoContextBot.core.command.Command;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandController {
    CommandFactoryBehavior commandFactory;
    public CommandController(TelegramBot bot, Update update) {
        commandFactory = new CommandFactoryBehavior(bot, update);
    }

    public void executeCommand(String command) {
        commandFactory(command).execute();
    }

    private Command commandFactory(String command) {
        return commandFactory.getCommand(command);
    }
}
