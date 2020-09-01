package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.Update;

public class InfoCommand implements Command {
    private Storage storage;
    private BotMessageSender messageSender;

    public InfoCommand(Storage storage, BotMessageSender messageSender) {
        this.storage = storage;
        this.messageSender = messageSender;
    }

    @Override
    public void execute(Update update) {
        String msg = "*Command not available*";
        long chatId = update.message().chat().id();
        messageSender.sendMessage(chatId, msg);
    }
}
