package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.contentProvider.ContextReversoContentProvider;
import com.Erag0.ReversoContextBot.domain.Storage;

public class CommandFactory {
    private Storage storage;
    private BotMessageSender messageSender;
    private ContextReversoContentProvider contentProvider;

    public CommandFactory(Storage storage, BotMessageSender messageSender, ContextReversoContentProvider contentProvider) {
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
