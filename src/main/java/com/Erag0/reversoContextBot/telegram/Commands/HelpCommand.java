package com.Erag0.reversoContextBot.telegram.Commands;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class HelpCommand implements Command {
    private static TelegramBot bot;
    private static Update update;

    public HelpCommand(TelegramBot bot, Update update) {
       this.bot = bot;
       this.update = update;
    }

    public void execute() {
        bot.execute(new SendMessage(update.message().chat().id(), "help command"));
    }
}
