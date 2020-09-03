package com.Erag0.ReversoContextBot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BotMessageSender {
    private TelegramBot bot;

    public void sendMessage(long chatId, String message) {
        bot.execute(new SendMessage(chatId, message)
                .parseMode(ParseMode.Markdown)
        );
    }

    public void sendMessageWithInlineKeyboard(long chatId, String message, Keyboard keyboard) {
        bot.execute(new SendMessage(chatId, message)
                .parseMode(ParseMode.Markdown)
                .replyMarkup(keyboard)
        );
    }
}
