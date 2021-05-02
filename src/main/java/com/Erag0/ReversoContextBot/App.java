package com.Erag0.ReversoContextBot;

import com.Erag0.ReversoContextBot.bot.ReversoContextBot;
import com.Erag0.ReversoContextBot.db.Storage;
import com.Erag0.ReversoContextBot.db.UserRepository;

public class App {

	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		Storage storage = new Storage(userRepository);

		ReversoContextBot bot = new ReversoContextBot(storage);
		bot.run();
	}
	
}
