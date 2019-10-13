package com.yevheniikukhol.reversoContextBot.reversoParser;

public class Text {
	private String translate;
	private String examples;
	private String finalText;

	public Text(String trans, String example){
		translate = trans;
		examples = example;
	}

	public void setTranslate(String trans){
		translate = trans;
	}

	public void setExamples(String exampls){
		examples = exampls;
	}

	public String getFinalText() throws Exception{
		generateFinalText();
		return finalText;
	}

	public void print() {
		System.out.println(translate);
		System.out.println(examples);
	}

	private void generateFinalText() throws Exception{
		
		//finalText = translate + "\n\r" + examples;
		finalText = examples;
	}
}