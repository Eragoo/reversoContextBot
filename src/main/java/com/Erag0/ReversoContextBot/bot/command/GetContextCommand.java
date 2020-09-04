package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.bot.callback.Language;
import com.Erag0.ReversoContextBot.contentProvider.ContentProvider;
import com.Erag0.ReversoContextBot.domain.Storage;
import com.pengrad.telegrambot.model.Update;

public class GetContextCommand implements Command{
    public static final CommandName NAME = CommandName.PARSE;

    private Storage storage;
    private BotMessageSender messageSender;
    private ContentProvider contentProvider;

    public GetContextCommand(Storage storage, BotMessageSender messageSender, ContentProvider contentProvider) {
        this.storage = storage;
        this.messageSender = messageSender;
        this.contentProvider = contentProvider;

    }
    public void execute(Update update) {
        long chatId = update.message().chat().id();
        String phrase = update.message().text();
        Language lang = storage.getLanguage(chatId);
        String message;
        message = contentProvider.getContent(lang, phrase);
        messageSender.sendMessage(chatId, message);
    }
}
