package com.Erag0.ReversoContextBot.bot;


import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.CommandFactory;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandController {
    CommandFactory commandFactory;
    public CommandController(Update update, Storage storage, BotMessageSender messageSender) {
        commandFactory = new CommandFactory(update, storage, messageSender);
    }

    public void executeCommand(String command) {
        commandFactory(command).execute();
    }

    private Command commandFactory(String command) {
        return commandFactory.getCommand(command);
    }
}
