package com.Eragoo.ReversoContextBot.command;

import com.Eragoo.ReversoContextBot.db.UserRepository;
import com.pengrad.telegrambot.model.Update;

import java.util.Arrays;
import java.util.Objects;

public class CommandFactory {
    private final UserRepository repository;

    public CommandFactory(UserRepository repository) {
        this.repository = repository;
    }

    public static Command getCommand(Update update, UserRepository repository) {
        CommandName commandName = getCommandName(update);
        CommandFactory commandFactory = new CommandFactory(repository);
        return commandFactory.getCommand(commandName);
    }

    private Command getCommand(CommandName commandName) {
        if (StartCommand.NAME.equals(commandName)) {
            return new StartCommand();
        } else if (GetContextCommand.NAME.equals(commandName)) {
            return new GetContextCommand(repository);
        } else if (SetLanguageCommand.NAME == commandName) {
            return new SetLanguageCommand(repository);
        }
        return new HelpCommand();
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
