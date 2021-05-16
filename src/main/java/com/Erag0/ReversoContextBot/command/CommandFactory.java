package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.db.Storage;

public class CommandFactory {
    private final Storage storage;

    public CommandFactory(Storage storage) {
        this.storage = storage;
    }

    public Command getCommand(CommandName commandName) {
        if (StartCommand.NAME.equals(commandName)) {
            return new StartCommand();
        } else if (GetContextCommand.NAME.equals(commandName)) {
            return new GetContextCommand(storage);
        } else if (SetLanguageCommand.NAME == commandName) {
            return new SetLanguageCommand(storage);
        }
        return new HelpCommand();
    }
}
