package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.Storage;

public class CommandFactory {
    private Storage storage;
    private BotMessageSender messageSender;

    public CommandFactory(Storage storage, BotMessageSender messageSender) {
        this.storage = storage;
        this.messageSender = messageSender;
    }

    public Command getCommand(CommandName commandName) {
        if ("/start".equals(commandName.getName())) {
            return new StartCommand(messageSender);
        } else if ("/parse".equals(commandName.getName())) {
            return new GetContextCommand(storage, messageSender);
        }
        return new HelpCommand(messageSender);
    }
}
