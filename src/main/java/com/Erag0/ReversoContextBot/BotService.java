package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.command.Command;
import com.Erag0.ReversoContextBot.command.CommandParser;
import com.Erag0.ReversoContextBot.db.Storage;
import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BotService {
    private final Storage storage;

    public Message consumeUpdate(Update update) {
        String messageText = update.message().text();
        Command command = CommandParser.getCommand(messageText, storage);
        return command.execute(update);
    }
}
