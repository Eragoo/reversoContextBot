package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.db.Storage;
import lombok.NonNull;

import java.util.Arrays;

public class CommandParser {
    public static Command getCommand(String message, Storage storage) {
        CommandName commandName = getCommandName(message);
        CommandFactory commandFactory = new CommandFactory(storage);
        return commandFactory.getCommand(commandName);
    }

    private static CommandName getCommandName(@NonNull String message) {
        String trimmedMsg = message.trim();
        if (isRegularCommand(trimmedMsg)) {
            String enumCommandName = trimmedMsg.substring(1).toUpperCase();
            return CommandName.valueOf(enumCommandName);
        }
        return CommandName.PARSE;
    }

    private static boolean isRegularCommand(String trimmed) {
        return Arrays.stream(CommandName.values())
                .map(CommandName::getName)
                .anyMatch(commandName -> commandName.equals(trimmed));
    }


}