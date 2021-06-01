package com.Eragoo.ReversoContextBot.command;

import com.Eragoo.ReversoContextBot.Message;
import com.pengrad.telegrambot.model.Update;

public class HelpCommand implements Command {
    public static final CommandName NAME = CommandName.HELP;

    public Message execute(Update update) {
        long chatId = update.message().chat().id();
        String msg = "*Введите /start чтобы начать!*\n*_По всем вопросам обращайтесь к @Erag0Contactbot_";
        return new Message(chatId, msg);
    }
}
