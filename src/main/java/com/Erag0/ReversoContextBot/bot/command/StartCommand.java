package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;

public class StartCommand implements Command{
    private static Update update;
    private Storage storage;
    public static final String NAME = "/start";
    private BotMessageSender messageSender;

    public StartCommand(Update update, Storage storage, BotMessageSender messageSender) {
        this.update = update;
        this.storage = storage;
        this.messageSender = messageSender;
    }

    public void execute() {
        long chatId = update.message().chat().id();

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[][]{{
                        new InlineKeyboardButton("🇬🇧En - Ru🇷🇺").callbackData("english-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - En🇬🇧").callbackData("russian-english")},{
                        new InlineKeyboardButton("🇩🇪Ge - Ru🇷🇺").callbackData("german-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - Ge🇩🇪").callbackData("russian-german")},{
                        new InlineKeyboardButton("🇯🇵Jp - Ru🇷🇺").callbackData("japanese-russian"),
                        new InlineKeyboardButton("🇷🇺Ru - Jp🇯🇵").callbackData("russian-japanese")}
                });
        String messageText = "*Привет👻*" + "\n" +
                "*Я умею подставлять введенное слово в контекст выбраного тобой языка*✏️" + "\n" +
                "*Выбери язык и введи желаемое слово/фразу*📝";

        messageSender.sendMessageWithInlineKeyboard(chatId, messageText, inlineKeyboard);
    }
    public String toString() {
        return NAME;
    }
}
