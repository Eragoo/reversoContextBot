package com.Erag0.ReversoContextBot.command;

import com.Erag0.ReversoContextBot.Message;
import com.Erag0.ReversoContextBot.Language;
import com.Erag0.ReversoContextBot.db.UserRepository;
import com.Erag0.ReversoContextBot.parser.ParseResult;
import com.Erag0.ReversoContextBot.parser.WebParser;
import com.pengrad.telegrambot.model.Update;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetContextCommand implements Command {
    public static final CommandName NAME = CommandName.PARSE;

    private final UserRepository repository;

    public GetContextCommand(UserRepository repo) {
        this.repository = repo;
    }

    public Message execute(Update update) {
        long chatId = update.message().chat().id();
        String phrase = update.message().text();
        Optional<String> lang = repository.getLanguage(chatId);
        String message = "";
        try {
            if (lang.isPresent()) {
                String language = Language.valueOf(lang.get()).getFullName();

                message = WebParser.getTranslation(language, phrase)
                        .stream()
                        .map(ParseResult::getFinalText)
                        .collect(Collectors.joining());
            } else {
                message = "*Для начала работы стоит выбрать язык*";
            }
        } catch (IOException ex) {
            message = "*Такой фразы нет в нашей базе*😢\n*Пожалуйста, попробуй другую формулировку*";
            return new Message(chatId, message);
        }
        return new Message(chatId, message);
    }
}
