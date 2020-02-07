package com.Erag0.ReversoContextBot.TelegramBotCore;


import com.Erag0.ReversoContextBot.Logger.Logger;
import com.Erag0.ReversoContextBot.TelegramBotCore.CallbackHandlers.LangCallbackHandler;
import com.Erag0.ReversoContextBot.Util.GetCommandFromMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class ReversoContextBot {
    private static final String TOKEN = "592148368:AAE59ohkGYvBjkJLt-zrmmtPKYANRtSrTYY";
    private TelegramBot bot;

    public ReversoContextBot() {
        this.bot = new TelegramBot(TOKEN);
    }

    public void Start() {
        BotCore();
    }

    private void BotCore() {
        this.bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try{
                    CommandController commandController = new CommandController(bot, update);
                    LangCallbackHandler handler = new LangCallbackHandler(bot, update);

                    if (update.message() != null ){
                        commandController.executeCommand(GetCommandFromMessage.getCommand(update.message().text()));
                    }else if (update.callbackQuery() != null) {
                        Logger.Log("info","Logs.txt", update.toString());
                        handler.execute(update.callbackQuery().data());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.Log("error", "Logs.txt",ex.getStackTrace());
                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}