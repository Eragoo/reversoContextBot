package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.db.UserRepository;

public class App {

	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		BotService botService = new BotService(userRepository);
		BotController botController = new BotController(botService);

		BotFrontController bot = new BotFrontController(botController);
		bot.run();
	}
	
}
