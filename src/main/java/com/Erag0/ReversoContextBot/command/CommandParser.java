package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.db.Storage;
import com.pengrad.telegrambot.model.Update;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Objects;

public class CommandParser {
    public static Command getCommand(Update update, Storage storage) {
        CommandName commandName = getCommandName(update);
        CommandFactory commandFactory = new CommandFactory(storage);
        return commandFactory.getCommand(commandName);
    }

    private static CommandName getCommandName(Update update) {
        if (update.callbackQuery() != null) {
            return CommandName.SET_LANGUAGE;
        } else if (update.message() != null && isRegularCommand(update.message().text().trim())) {
            String enumCommandName = update.message().text().trim().substring(1).toUpperCase();
            return CommandName.valueOf(enumCommandName);
        }
        return CommandName.PARSE;
    }

    private static boolean isRegularCommand(String trimmed) {
        return Arrays.stream(CommandName.values())
                .map(CommandName::getName)
                .filter(Objects::nonNull)
                .anyMatch(commandName -> commandName.equals(trimmed));
    }
}
