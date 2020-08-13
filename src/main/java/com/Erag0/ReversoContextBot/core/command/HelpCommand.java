package com.Erag0.ReversoContextBot.core.command;

import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class HelpCommand implements Command {
    private static TelegramBot bot;
    private static Update update;
    private Storage storage;
    public final String NAME = "/help";

    public HelpCommand(TelegramBot bot, Update update) {
       this.bot = bot;
       this.update = update;
       storage = new Storage(update);
    }

    public void execute() {
        bot.execute(new SendMessage(update.message().chat().id(), "*Введите /start чтобы начать!*\n*_По всем вопросам обращайтесь к @Erag0Contactbot_")
        .parseMode(ParseMode.Markdown)
                );
        storage.StoreCommand(NAME);
    }

    public String toString() {
        return NAME;
    }
}
