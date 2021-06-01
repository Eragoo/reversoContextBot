package com.Eragoo.ReversoContextBot;

import com.Eragoo.ReversoContextBot.db.UserRepository;

public class App {

	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		BotService botService = new BotService(userRepository);
		BotController botController = new BotController(botService);

		BotFrontController bot = new BotFrontController(botController);
		bot.run();
	}
}
