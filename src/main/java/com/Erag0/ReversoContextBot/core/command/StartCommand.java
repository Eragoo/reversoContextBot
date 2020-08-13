package com.Erag0.ReversoContextBot.core.command;

import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;

public class StartCommand implements Command{
    private static TelegramBot bot;
    private static Update update;
    private Storage storage;
    public static final String NAME = "/start";

    public StartCommand(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
        this.storage = new Storage(update);
    }

    public void execute() {
        long chat_id = update.message().chat().id();
        StringBuilder messageText = new StringBuilder();
        messageText.append("*Привет👻*" + "\n");
        messageText.append("*Я умею подставлять введенное слово в контекст выбраного тобой языка*✏️" + "\n");
        messageText.append("*Выбери язык и введи желаемое слово/фразу*📝");

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[][]{{
                        new InlineKeyboardButton("🇬🇧En - Ru🇷🇺").callbackData("english-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - En🇬🇧").callbackData("russian-english")},{
                        new InlineKeyboardButton("🇩🇪Ge - Ru🇷🇺").callbackData("german-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - Ge🇩🇪").callbackData("russian-german")},{
                        new InlineKeyboardButton("🇯🇵Jp - Ru🇷🇺").callbackData("japanese-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - Jp🇯🇵").callbackData("russian-japanese")}
                });
        bot.execute(new SendMessage(chat_id, messageText.toString())
                .parseMode(ParseMode.Markdown)
                .replyMarkup(inlineKeyboard)
        );

        storage.StoreCommand(NAME);
    }
    public String toString() {
        return NAME;
    }
}
