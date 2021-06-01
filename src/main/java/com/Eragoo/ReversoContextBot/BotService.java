package com.Eragoo.ReversoContextBot;

import com.Eragoo.ReversoContextBot.command.Command;
import com.Eragoo.ReversoContextBot.command.CommandFactory;
import com.Eragoo.ReversoContextBot.db.UserRepository;
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
