package com.Erag0.ReversoContextBot.TelegramBotCore.CallbackHandlers;

import com.Erag0.ReversoContextBot.Logger.Logger;
import com.Erag0.ReversoContextBot.Util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class LangCallbackHandler implements Handler{
    private static Storage storage;
    private static TelegramBot bot;
    private static  Update update;

    public LangCallbackHandler(TelegramBot bot, Update update) {
        storage = new Storage(update);
        LangCallbackHandler.update = update;
        LangCallbackHandler.bot = bot;
    }

    public void execute(String query) {
        Logger.Log("info","Logs.txt","Trying to store lang!");
        storage.StoreLang(query);
        Logger.Log("info","Logs.txt","Lang stored!");
        SendSuccessMessage();
    }
    private void SendSuccessMessage() {
        String messageText = "*Язык перевода задан*✨\n";
        bot.execute(new SendMessage(update.callbackQuery().from().id(), messageText)
                .parseMode(ParseMode.Markdown)
        );
    }
}
