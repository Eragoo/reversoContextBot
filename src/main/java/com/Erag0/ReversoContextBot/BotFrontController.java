package com.Erag0.ReversoContextBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.java.Log;

import static com.Erag0.ReversoContextBot.config.BotConfig.TOKEN;

@Log
public class BotFrontController {
    private final BotController botController;
    private final TelegramBot bot;

    public BotFrontController(BotController controller) {
        this.bot = new TelegramBot(TOKEN);
        this.botController = controller;
    }

    public void run() {
        log.info("Application started!");
        bot.setUpdatesListener(updatesListener());
    }

    private UpdatesListener updatesListener() {
        return updates -> {
            for (Update update : updates) {
                Message message = botController.consumeUpdate(update);
                sendResponse(message);

                log.info(update.toString());
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }

    private void sendResponse(Message message) {
        SendMessage sendMessage = new SendMessage(message.getChatId(), message.getMessage())
                .parseMode(ParseMode.Markdown);
        if (message.isHasKeyboard()) {
            sendMessage.replyMarkup(message.getKeyboard());
        }

        bot.execute(sendMessage);
    }
}