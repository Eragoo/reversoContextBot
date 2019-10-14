package com.yevheniikukhol.reversoContextBot.telegram;

import com.yevheniikukhol.reversoContextBot.telegram.commands.Start;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.yevheniikukhol.reversoContextBot.reversoParser.Parser;
import java.util.*;
import java.io.IOException;
import com.yevheniikukhol.reversoContextBot.DB.MyDatabase;

public class MyBot extends TelegramLongPollingBot {

	public void onUpdateReceived(Update update){
		if (update.hasMessage() && update.getMessage().hasText()) {
		    String message = update.getMessage().getText();
		    switch (message){
                case "/start":
                    StartCommand(update);
                    break;
                case "/help":
                    HelpCommand(update);
                    break;
                default:
                    Translate(update);
                    break;
            }
		}else if (update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();
            switch (call_data){
                case "choose-lang":
                    StartHelper(update);
                    break;
            }
		}
	}

	private void Translate(Update update){
	    String message_text = update.getMessage().getText();
	    long chat_id = update.getMessage().getChatId();
	    try{
            String translatedRes = getTranslatedContent(parseMessageText(message_text).get("lang"),parseMessageText(message_text).get("word"));
            SendMessage message = new SendMessage()
                    .setChatId(chat_id)
                    .setText(translatedRes);
            execute(message);
        }catch (TelegramApiException e){
	        e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	private void StartHelper(Update update) {
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
        String answer = "*Если хочешь воспользоваться моими услугами, то отправь мне сообщения вида:*\n\r*lang1-lang2 word-to-translate*\n\r*Например:*\n\r*english-russian hello-world*";
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(answer)
                .enableMarkdown(true);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

	private void StartCommand(Update update){
		if (update.hasMessage() || update.getMessage().hasText()) {
		    String toSend = "*Привет!*\uD83C\uDF38 \n\r_Я умею умею делать практически все то же самое что и сайт reverso-context_\n\r*Если хочешь воспользоваться моими услугами, то отправь мне сообщения вида:*" +
                    "\n" +
                    "*lang1-lang2 word-to-translate*" +
                    "\n" +
                    "*Например:*" +
                    "\n" +
                    "*english-russian hello-world*";
			SendMessage message = new SendMessage()
					.setChatId(update.getMessage().getChatId())
					.setText(toSend)
                    .enableMarkdown(true);

			InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
			List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
			List<InlineKeyboardButton> rowInLine = new ArrayList<>();
			rowInLine.add(new InlineKeyboardButton().setText("▶️Список доступных языков◀️").setCallbackData("choose-lang"));
			rowsInLine.add(rowInLine);
			markupInline.setKeyboard(rowsInLine);
			message.setReplyMarkup(markupInline);
			try{
				execute(message);
			} catch (TelegramApiException ex){
				ex.printStackTrace();
			}
		}
	}

	private void HelpCommand(Update update){
		long chat_id = update.getMessage().getChatId();
		String text = "Пока что можно возспоьзоваться только коммандой /start и /help\n\rНо всё ещё впереди!";
		SendMessage send = new SendMessage()
				.setChatId(chat_id)
				.setText(text)
                .enableMarkdown(true);

		try{
			execute(send);
		} catch (TelegramApiException ex){
			ex.printStackTrace();
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