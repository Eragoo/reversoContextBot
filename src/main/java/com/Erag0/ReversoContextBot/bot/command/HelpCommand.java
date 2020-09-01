package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.Update;

public class HelpCommand implements Command {
    private Storage storage;
    public final String NAME = "/help";
    private BotMessageSender messageSender;

    public HelpCommand(Storage storage, BotMessageSender messageSender) {
        this.storage = storage;
        this.messageSender = messageSender;
    }

    public void execute(Update update) {
        long chatId = update.message().chat().id();
        String msg = "*Введите /start чтобы начать!*\n*_По всем вопросам обращайтесь к @Erag0Contactbot_";
        messageSender.sendMessage(chatId, msg);
    }

    public String toString() {
        return NAME;
    }
}
