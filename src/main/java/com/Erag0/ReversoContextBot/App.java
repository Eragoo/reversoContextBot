package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.db.Storage;
import com.Erag0.ReversoContextBot.db.UserRepository;

public class App {

	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		Storage storage = new Storage(userRepository);
		BotService botService = new BotService(storage);
		BotController botController = new BotController(botService);

		BotFrontController bot = new BotFrontController(botController);
		bot.run();
	}
	
}
