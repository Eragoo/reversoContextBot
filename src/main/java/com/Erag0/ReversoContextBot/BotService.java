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
        Command command = CommandParser.getCommand(update, storage);
        return command.execute(update);
    }
}
