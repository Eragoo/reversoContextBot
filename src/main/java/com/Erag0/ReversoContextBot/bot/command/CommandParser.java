package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.Storage;
import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public static Command getCommand(String message, Storage storage, BotMessageSender messageSender) {
        String commandName = getCommandName(message);
        CommandFactory commandFactory = new CommandFactory(storage, messageSender);
        return commandFactory.getCommand(commandName);
    }

    private static String getCommandName(@NonNull String message) {
        String trimmed = message.trim();
        Pattern p = Pattern.compile("(/\\S+)");
        Matcher matcher = p.matcher(trimmed);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Message: " + message + "doesn't contain known bot command");
    }

}
