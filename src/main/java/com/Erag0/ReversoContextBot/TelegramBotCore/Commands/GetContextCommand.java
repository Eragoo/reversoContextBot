package com.Erag0.ReversoContextBot.TelegramBotCore.Commands;

import com.Erag0.ReversoContextBot.Logger.Logger;
import com.Erag0.ReversoContextBot.Parser.Parser;
import com.Erag0.ReversoContextBot.Util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;

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
        String phrase = update.message().text();
        long chat_id = update.message().chat().id();
        String message = "";
        Parser parser = new Parser(lang, phrase);
        try{
            message = parser.getText();
        } catch (IOException ex) {
            Logger.Log("error","Logs.txt",ex.getMessage());
            message = "*Такой фразы нет в нашей базе*😢\n*Пожалуйста, попробуй другую формулировку*";
        } finally {
            bot.execute(new SendMessage(chat_id, message)
                 .parseMode(ParseMode.Markdown)
            );
        }
        storage.StoreCommand(NAME);
    }
}
