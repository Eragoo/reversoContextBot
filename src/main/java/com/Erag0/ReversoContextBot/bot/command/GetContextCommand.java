package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.parser.Parser;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.model.Update;

import java.io.IOException;

public class GetContextCommand implements Command{
    private Storage storage;
    private BotMessageSender messageSender;

    public GetContextCommand(Storage storage, BotMessageSender messageSender) {
        this.storage = storage;
        this.messageSender = messageSender;
    }
    public void execute(Update update) {
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
            messageSender.sendMessage(chatId, message);
        }
    }
}
