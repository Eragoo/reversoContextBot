package com.yevheniikukhol.reversoContextBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.yevheniikukhol.reversoContextBot.telegram.MyBot;

public class App {
	
    public static void main(String[] args){
    	ApiContextInitializer.init();

    	TelegramBotsApi botsApi = new TelegramBotsApi();

    	try{
    		botsApi.registerBot(new MyBot());
    	} catch (TelegramApiException e){
    		e.printStackTrace();
    	}
  	}
    
}
