package com.Erag0.ReversoContextBot.TelegramBotCore;


import com.Erag0.ReversoContextBot.Factories.CommandFactoryBehavior;
import com.Erag0.ReversoContextBot.TelegramBotCore.Commands.Command;
import com.Erag0.ReversoContextBot.Util.Storage;
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
