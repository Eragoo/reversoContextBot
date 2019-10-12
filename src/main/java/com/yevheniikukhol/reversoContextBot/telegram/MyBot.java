package com.yevheniikukhol.reversoContextBot.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.yevheniikukhol.reversoContextBot.reversoParser.Parser;
import java.util.*;
import java.io.IOException;

public class MyBot extends TelegramLongPollingBot {
	public void onUpdateReceived(Update update){
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			try{
				String translatedRes = getTranslatedContent(parseMessageText(message_text).get("lang"),parseMessageText(message_text).get("word"));
				SendMessage message = new SendMessage()
					.setChatId(chat_id)
					.setText(translatedRes);

				execute(message);
			} catch(TelegramApiException ex){
				ex.printStackTrace();
			} catch(IOException ex) {
				ex.printStackTrace();
			} catch(Exception ex){
				ex.printStackTrace();
			} 
		}
	}

	public String getBotUsername() {
		return "Training";
	}

	public String getBotToken() {
		return "592148368:AAE59ohkGYvBjkJLt-zrmmtPKYANRtSrTYY";
	}

	private HashMap<String,String> parseMessageText(String message) {
		HashMap<String,String> params = new HashMap<>();
		String[] paramsArr = message.split(" ");
		params.put("lang", paramsArr[0].trim());
		params.put("word", paramsArr[1].trim());
		return params;
	}

	private String getTranslatedContent(String lang, String word) throws IOException, Exception{
		Parser parser = new Parser(lang, word);
		return parser.getText().getFinalText();
	}
}