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

    public Command getCommand(String commandName) {
        if ("/help".equals(commandName)) {
            return new HelpCommand(messageSender);
        } else if ("/start".equals(commandName)) {
            return new StartCommand(messageSender);
        }
        return new GetContextCommand(storage, messageSender);
    }
}
