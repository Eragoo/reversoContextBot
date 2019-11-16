package com.Erag0.ReversoContextBot.TelegramBotCore.Commands;

import com.Erag0.ReversoContextBot.Parser.Parser;
import com.Erag0.ReversoContextBot.Util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class GetContextCommand implements Command{
    private TelegramBot bot;
    private Update update;
    private Storage storage;
    private Parser parser;
    public static final String NAME = "/get";

    public GetContextCommand(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
        storage = new Storage(update);
    }
    public void execute() {
        String lang = storage.RestoreLang();
        String messageText = "restore lang " + lang;
        bot.execute(new SendMessage(update.message().chat().id(), messageText));
    }
}
