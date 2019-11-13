package com.Erag0.ReversoContextBot.TelegramBotCore.Commands;

import com.Erag0.ReversoContextBot.Util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class HelpCommand implements Command {
    private static TelegramBot bot;
    private static Update update;
    private Storage storage;

    private final String NAME = "/help";

    public HelpCommand(TelegramBot bot, Update update) {
       this.bot = bot;
       this.update = update;
       storage = new Storage(update);
    }

    public void execute() {
        String command = storage.RestoreCommand();
        bot.execute(new SendMessage(update.message().chat().id(), "Restored command : " + command + ". Command " + NAME + " stored!"));
        storage.StoreCommand(NAME);
    }

    public String toString() {
        return NAME;
    }
}
