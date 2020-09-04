package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.contentProvider.ContentProvider;
import com.Erag0.ReversoContextBot.domain.Storage;

public class CommandFactory {
    private Storage storage;
    private BotMessageSender messageSender;
    private ContentProvider contentProvider;

    public CommandFactory(Storage storage, BotMessageSender messageSender, ContentProvider contentProvider) {
        this.storage = storage;
        this.messageSender = messageSender;
        this.contentProvider = contentProvider;
    }

    public Command getCommand(CommandName commandName) {
        if (StartCommand.NAME.equals(commandName)) {
            return new StartCommand(messageSender);
        } else if (GetContextCommand.NAME.equals(commandName)) {
            return new GetContextCommand(storage, messageSender, contentProvider);
        }
        return new HelpCommand(messageSender);
    }
}
