package com.Erag0.ReversoContextBot.bot.command;

import com.Erag0.ReversoContextBot.bot.BotMessageSender;
import com.Erag0.ReversoContextBot.bot.callback.Language;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;

public class StartCommand implements Command {
    public static final CommandName NAME = CommandName.START;
    private BotMessageSender messageSender;

    public StartCommand(BotMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void execute(Update update) {
        long chatId = update.message().chat().id();

        InlineKeyboardMarkup inlineKeyboard = getInlineKeyboard();

        String messageText = "*Привет👻*" + "\n" +
                "*Я умею подставлять введенное слово в контекст выбраного тобой языка*✏️" + "\n" +
                "*Выбери язык и введи желаемое слово/фразу*📝";

        messageSender.sendMessageWithInlineKeyboard(chatId, messageText, inlineKeyboard);
    }

    private InlineKeyboardMarkup getInlineKeyboard() {
        return new InlineKeyboardMarkup(
                    new InlineKeyboardButton[][]{{
                            new InlineKeyboardButton("🇬🇧En - Ru🇷🇺").callbackData(Language.EN_RU.toString()),
                            new InlineKeyboardButton("🇷🇺Ru - En🇬🇧").callbackData(Language.RU_EN.toString())
                    }, {
                            new InlineKeyboardButton("🇩🇪Ge - Ru🇷🇺").callbackData(Language.GE_RU.toString()),
                            new InlineKeyboardButton("🇷🇺Ru - Ge🇩🇪").callbackData(Language.RU_GE.toString())
                    }, {
                            new InlineKeyboardButton("🇯🇵Jp - Ru🇷🇺").callbackData(Language.JP_RU.toString()),
                            new InlineKeyboardButton("🇷🇺Ru - Jp🇯🇵").callbackData(Language.RU_JP.toString())
                    }});
    }
}
