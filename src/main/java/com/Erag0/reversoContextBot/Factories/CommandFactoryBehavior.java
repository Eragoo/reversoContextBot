package com.Erag0.ReversoContextBot.Factories;

import com.Erag0.ReversoContextBot.TelegramBotCore.Commands.Command;
import com.Erag0.ReversoContextBot.TelegramBotCore.Commands.GetContextCommand;
import com.Erag0.ReversoContextBot.TelegramBotCore.Commands.HelpCommand;
import com.Erag0.ReversoContextBot.TelegramBotCore.Commands.StartCommand;
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
