package com.Erag0.ReversoContextBot.factories;

import com.Erag0.ReversoContextBot.core.command.Command;
import com.Erag0.ReversoContextBot.core.command.GetContextCommand;
import com.Erag0.ReversoContextBot.core.command.HelpCommand;
import com.Erag0.ReversoContextBot.core.command.StartCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public class CommandFactoryBehavior {
    private TelegramBot bot;
    private Update update;

    public CommandFactoryBehavior(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
    }

    public Command getCommand(String command) {
        if ("/help".equals(command)) {
            return new HelpCommand(bot, update);
        } else if ("/start".equals(command)) {
            return new StartCommand(bot, update);
        }
        return new GetContextCommand(bot, update);
    }
}
