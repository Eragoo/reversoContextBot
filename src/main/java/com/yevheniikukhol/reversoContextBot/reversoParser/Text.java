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
		//byte[] bytes = "\xF0\x9F\x98\x81".getBytes();
		//String emoji = new String(bytes, "UTF-8");
		finalText = translate + "\n\r" + examples;
	}
}