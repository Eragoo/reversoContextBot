package com.yevheniikukhol.reversoContextBot.telegram;

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
	private String langToTranslate = "";

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
                case "lang-list":
                    StartHelper(update);
                    break;
				default:
					TranslateHelper(update);
					break;
            }
		}
	}

	private void Translate(Update update){
		long chat_id = update.getMessage().getChatId();
		String message_text = update.getMessage().getText();
		if (!langToTranslate.equals(" ") && update.getMessage().hasText()){
			try{
				String translatedRes = getTranslatedContent(langToTranslate, message_text);
				SendMessage message = new SendMessage()
						.setChatId(chat_id)
						.setText(translatedRes);
				execute(message);
			}catch (TelegramApiException e){
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			try{
				SendMessage message = new SendMessage()
						.setChatId(chat_id)
						.setText("Язык перевода не задан :(\n Нажмите /start");
				execute(message);
			}catch (TelegramApiException e){
				e.printStackTrace();
			}
		}


    }

	private void StartHelper(Update update) {
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		String answer = "*Выберете язык:*";
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(answer)
                .enableMarkdown(true);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        rowInLine.add(new InlineKeyboardButton().setText("️\uD83C\uDDEC\uD83C\uDDE7Английский - Русский\uD83C\uDDF7\uD83C\uDDFA️").setCallbackData("english-russian"));
        rowInLine.add(new InlineKeyboardButton().setText("️\uD83C\uDDF7\uD83C\uDDFAРусский - Английский\uD83C\uDDEC\uD83C\uDDE7️").setCallbackData("russian-english"));
        rowsInLine.add(rowInLine);
        markupInline.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

	private void StartCommand(Update update){
		if (update.hasMessage() || update.getMessage().hasText()) {
		    String toSend = "*Привет!*\uD83C\uDF38 \n\r*Я умею делать практически все то же самое что и сайт reverso-context*\n\r*Если хочешь воспользоваться моими услугами, то для начала выбери язык для перевода, а потом введи слово или фразу, которую необходимо перевести*";
			SendMessage message = new SendMessage()
					.setChatId(update.getMessage().getChatId())
					.setText(toSend)
                    .enableMarkdown(true);

			InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
			List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
			List<InlineKeyboardButton> rowInLine = new ArrayList<>();
			rowInLine.add(new InlineKeyboardButton().setText("▶️Список доступных языков◀️").setCallbackData("lang-list"));
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

	private void TranslateHelper(Update update) {
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		langToTranslate = update.getCallbackQuery().getData();

		String answer = "*Введите фразу для перевода:*";
		SendMessage send = new SendMessage()
				.setChatId(chat_id)
				.setText(answer)
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

	private String getTranslatedContent(String lang, String word) throws Exception{
		Parser parser = new Parser(lang, word);
		return parser.getText().getFinalText();
	}
}