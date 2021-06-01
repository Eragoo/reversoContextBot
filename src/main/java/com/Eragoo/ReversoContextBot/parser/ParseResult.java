package com.Eragoo.ReversoContextBot.parser;

import lombok.Getter;

@Getter
public class ParseResult {
	private final String sourceExample;
	private final String translatedExample;
	private final String finalText;

	public ParseResult(String sourceExample, String translatedExample){
		this.sourceExample = sourceExample;
		this.translatedExample = translatedExample;
		this.finalText =  "💬" + sourceExample + "\n\r" +
				"🗨" + translatedExample + "\n\r" + "\n\r";
	}
}
