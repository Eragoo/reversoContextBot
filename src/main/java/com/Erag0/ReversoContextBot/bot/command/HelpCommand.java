package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.Update;

public class HelpCommand implements Command {
    private BotMessageSender messageSender;

    public HelpCommand(BotMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void execute(Update update) {
        long chatId = update.message().chat().id();
        String msg = "*Введите /start чтобы начать!*\n*_По всем вопросам обращайтесь к @Erag0Contactbot_";
        messageSender.sendMessage(chatId, msg);
    }
}
