package com.Erag0.ReversoContextBot.factories;

import com.Erag0.ReversoContextBot.bot.command.Command;
import com.Erag0.ReversoContextBot.bot.command.GetContextCommand;
import com.Erag0.ReversoContextBot.bot.command.HelpCommand;
import com.Erag0.ReversoContextBot.bot.command.StartCommand;
import com.Erag0.ReversoContextBot.util.Storage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandFactoryBehavior {
    private TelegramBot bot;
    private Update update;
    private Storage storage;

    public CommandFactoryBehavior(TelegramBot bot, Update update, Storage storage) {
        this.bot = bot;
        this.update = update;
    }

    public Command getCommand(String command) {
        if ("/help".equals(command)) {
            return new HelpCommand(bot, update, storage);
        } else if ("/start".equals(command)) {
            return new StartCommand(bot, update, storage);
        }
        return new GetContextCommand(bot, update, storage);
    }
}
