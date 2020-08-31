package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.parser.Parser;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;

public class GetContextCommand implements Command{
    private TelegramBot bot;
    private Update update;
    private Storage storage;

    public GetContextCommand(TelegramBot bot, Update update, Storage storage) {
        this.bot = bot;
        this.update = update;
        this.storage = storage;
    }
    public void execute() {
        long chatId = update.message().chat().id();
        String phrase = update.message().text();
        String lang = storage.getLanguage(chatId);
        String message = "";
        Parser parser = new Parser(lang, phrase);
        try{
            message = parser.getText();
        } catch (IOException ex) {
            message = "*Такой фразы нет в нашей базе*😢\n*Пожалуйста, попробуй другую формулировку*";
        } finally {
            bot.execute(new SendMessage(chatId, message)
                 .parseMode(ParseMode.Markdown)
            );
        }
    }
}
