package com.Erag0.ReversoContextBot.bot;


import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.factories.CommandFactoryBehavior;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandController {
    CommandFactoryBehavior commandFactory;
    public CommandController(TelegramBot bot, Update update, Storage storage) {
        commandFactory = new CommandFactoryBehavior(bot, update, storage);
    }

    public void executeCommand(String command) {
        commandFactory(command).execute();
    }

    private Command commandFactory(String command) {
        return commandFactory.getCommand(command);
    }
}
