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

    public Command getCommand(String command) {
        if ("/help".equals(command)) {
            return new HelpCommand(storage, messageSender);
        } else if ("/start".equals(command)) {
            return new StartCommand(storage, messageSender);
        }
        return new GetContextCommand(storage, messageSender);
    }
}
