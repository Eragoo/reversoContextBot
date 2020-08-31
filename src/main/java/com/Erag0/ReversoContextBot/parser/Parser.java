package com.Erag0.ReversoContextBot.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Parser {

    private String word;
    private String lang;

    public Parser(String lang, String word) {
        this.word = UrlSpaceEncode(word.trim());
        this.lang = lang.trim();
    }

    public String getText() throws IOException {
        HashMap<String, String> parsedData = processData(parse());
        String text = new Text(parsedData.get("translate"), parsedData.get("examples")).getFinalText();

        return text;
    }

    private HashMap<String, Elements> parse() throws IOException {
        HashMap<String, Elements> elements = new HashMap<>();
        Document doc = Jsoup.connect("https://context.reverso.net/translation/" + this.lang + "/" + this.word).get();
        Elements translate = doc.select("div#translations-content");
        Elements nativeSentence = doc.select("div.src.ltr");
        Elements translatedSentence = doc.select("div.trg.ltr");
        elements.put("translate", translate);
        elements.put("examplesNative", nativeSentence);
        elements.put("examplesTrans", translatedSentence);

        return elements;
    }

    private String UrlSpaceEncode(String str) {
        return str.replace(" ", "-");
    }

    private HashMap<String, String> processData(HashMap<String, Elements> elems) {
        HashMap<String, String> results = new HashMap<>();
        String finalSentence = "";

        String translate = elems.get("translate").text();
        Iterator<Element> nativeIteratorSentence = elems.get("examplesNative").iterator();
        Iterator<Element> translatedIteratorSentence = elems.get("examplesTrans").iterator();
        int i = 0;
        while (nativeIteratorSentence.hasNext() && translatedIteratorSentence.hasNext()) {
            if (i > 5) {
                break;
            }
            finalSentence += "💬" + nativeIteratorSentence.next().text() + "\n\r";
            finalSentence += "🗨" + translatedIteratorSentence.next().text() + "\n\r" + "\n\r";
            i++;
        }

        results.put("translate", "📢" + translate + "📢" + "\n\r");
        results.put("examples", finalSentence);
        return results;
    }
}