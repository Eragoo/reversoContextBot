package com.Erag0.ReversoContextBot;

import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BotController {
    public final BotService botService;

    public Message consumeUpdate(Update update) {
        return botService.consumeUpdate(update);
    }
}
