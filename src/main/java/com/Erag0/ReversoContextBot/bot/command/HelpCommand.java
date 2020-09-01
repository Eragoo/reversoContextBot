package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class HelpCommand implements Command {
    private static Update update;
    private Storage storage;
    public final String NAME = "/help";
    private BotMessageSender messageSender;

    public HelpCommand(Update update, Storage storage, BotMessageSender messageSender) {
        this.update = update;
        this.storage = storage;
        this.messageSender = messageSender;
    }

    public void execute() {
        long chatId = update.message().chat().id();
        String msg = "*Введите /start чтобы начать!*\n*_По всем вопросам обращайтесь к @Erag0Contactbot_";
        messageSender.sendMessage(chatId, msg);
    }

    public String toString() {
        return NAME;
    }
}
