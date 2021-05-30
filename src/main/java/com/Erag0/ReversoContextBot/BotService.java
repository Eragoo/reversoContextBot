package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.command.Command;
import com.Erag0.ReversoContextBot.command.CommandFactory;
import com.Erag0.ReversoContextBot.db.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BotService {
    private final UserRepository storage;

    public Message consumeUpdate(Update update) {
        Command command = CommandFactory.getCommand(update, storage);
        return command.execute(update);
    }
}
