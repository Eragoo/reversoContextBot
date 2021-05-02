package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.bot.callback.Language;
import com.Erag0.ReversoContextBot.parser.ReversoContext;
import com.Erag0.ReversoContextBot.db.Storage;
import com.pengrad.telegrambot.model.Update;

import java.io.IOException;
import java.util.Optional;

public class GetContextCommand implements Command{
    public static final CommandName NAME = CommandName.PARSE;

    private final Storage storage;
    private final BotMessageSender messageSender;

    public GetContextCommand(Storage storage, BotMessageSender messageSender) {
        this.storage = storage;
        this.messageSender = messageSender;
    }
    public void execute(Update update) {
        long chatId = update.message().chat().id();
        String phrase = update.message().text();
        Optional<String> lang = storage.getLanguage(chatId);
        String message = "";
        try{
            if (lang.isPresent()) {
                message = ReversoContext.getTranslation(Language.valueOf(lang.get()).getFullName(), phrase);
            } else {
                message = "*Для начала работы стоит выбрать язык*";
            }
        } catch (IOException ex) {
            message = "*Такой фразы нет в нашей базе*😢\n*Пожалуйста, попробуй другую формулировку*";
        } finally {
            messageSender.sendMessage(chatId, message);
        }
    }
}
