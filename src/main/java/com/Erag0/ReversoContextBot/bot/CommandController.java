package com.Erag0.ReversoContextBot.bot;


import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.CommandFactory;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.Update;

public class CommandController {
    CommandFactory commandFactory;

    public CommandController(Storage storage, BotMessageSender messageSender) {
        commandFactory = new CommandFactory(storage, messageSender);
    }

    public void executeCommand(String commandName, Update update) {
        Command command = getCommand(commandName);
        command.execute(update);
    }

    private Command getCommand(String command) {
        return commandFactory.getCommand(command);
    }
}
