package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.model.Update;

public class CommandFactory {
    private Update update;
    private Storage storage;
    private BotMessageSender messageSender;

    public CommandFactory(Update update, Storage storage, BotMessageSender messageSender) {
        this.update = update;
        this.storage = storage;
        this.messageSender = messageSender;
    }

    public Command getCommand(String command) {
        if ("/help".equals(command)) {
            return new HelpCommand(update, storage, messageSender);
        } else if ("/start".equals(command)) {
            return new StartCommand(update, storage, messageSender);
        }
        return new GetContextCommand(update, storage, messageSender);
    }
}
