package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class InfoCommand implements Command {
    private static Update update;
    private Storage storage;
    public final String NAME = "/info";
    private BotMessageSender messageSender;

    public InfoCommand(Update update, Storage storage, BotMessageSender messageSender) {
        this.update = update;
        this.storage = storage;
        this.messageSender = messageSender;
    }

    @Override
    public void execute() {
        String msg = "*Command not available*";
        long chatId = update.message().chat().id();
        messageSender.sendMessage(chatId, msg);
    }
}
