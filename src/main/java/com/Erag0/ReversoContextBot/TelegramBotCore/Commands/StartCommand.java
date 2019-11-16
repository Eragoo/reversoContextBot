package com.Erag0.ReversoContextBot.TelegramBotCore.Commands;

import com.Erag0.ReversoContextBot.Util.Storage;
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
        String command = storage.RestoreCommand();

        StringBuilder messageText = new StringBuilder();
        messageText.append("*Привет!*" + "\n");
        messageText.append("*Моя основная функция - помогать тебе в изучении иностранного языка*" + "\n");
        messageText.append("*Я умею подставлять введенное слово в контекст выбраного тобой языка*" + "\n");
        messageText.append("_Какая в этом польза?_ - *можно узнать с помощью /about*" + "\n");
        messageText.append("*Для работы тебе нужно только выбрать язык и ввести желаемое слово/фразу*");
        messageText.append("*и в ответ ты получишь примеры употребления этого слова/фразы в живом контексте!*");

        System.out.println("Restored command : " + command + ". Command " + NAME + " stored!");


        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Английский - Русский").callbackData("english-russian"),
                        new InlineKeyboardButton("Русский - Английский").callbackData("russian-english"),
                        new InlineKeyboardButton("Английский - Русский").callbackData("english-russian"),
                        new InlineKeyboardButton("Русский - Английский").callbackData("russian-english"),
                });
        bot.execute(new SendMessage(update.message().chat().id(), messageText.toString())
                .parseMode(ParseMode.Markdown)
                .replyMarkup(inlineKeyboard)
        );

        storage.StoreCommand(NAME);
    }
    public String toString() {
        return NAME;
    }
}
