package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class InfoCommand implements Command {
    private static TelegramBot bot;
    private static Update update;
    private Storage storage;
    public final String NAME = "/info";

    public InfoCommand(TelegramBot bot, Update update, Storage storage) {
        this.bot = bot;
        this.update = update;
        this.storage = storage;
    }

    @Override
    public void execute() {
        bot.execute(new SendMessage(update.message().chat().id(), "*Info command*")
                .parseMode(ParseMode.Markdown)
        );
    }
}
