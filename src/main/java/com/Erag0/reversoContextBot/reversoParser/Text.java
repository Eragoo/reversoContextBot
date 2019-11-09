package com.Erag0.reversoContextBot.reversoParser;

public class Text {
	private String translate;
	private String examples;
	private String finalText;

	public Text(String translate, String examples){
		this.translate = translate;
		this.examples = examples;
	}

	public void setTranslate(String trans){
		this.translate = translate;
	}

	public void setExamples(String exampls){
		this.examples = examples;
	}

	public String getFinalText() {
		generateFinalText();
		return finalText;
	}

	public void print() {
		System.out.println(translate);
		System.out.println(examples);
	}

	private void generateFinalText() {
		finalText = examples;
	}
}