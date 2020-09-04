package com.Erag0.ReversoContextBot.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

public class Parser {

    private String word;
    private String lang;

    public Parser(String lang, String word) {
        this.word = encodeSpaces(word.trim());
        this.lang = lang.trim();
    }

    public String getText() throws IOException {
        return parse();
    }

    private String parse() throws IOException {
        Document doc = Jsoup.connect("https://context.reverso.net/translation/" + lang + "/" + word).get();
        Iterator<String> withoutTranslating = doc.select("div.src.ltr")
                .stream()
                .map(Element::text)
                .iterator();

        Iterator<String> withTranslating = doc.select("div.trg.ltr")
                .stream()
                .map(Element::text)
                .iterator();


        StringBuilder finalSentence = new StringBuilder();
        for (int i = 0; withoutTranslating.hasNext() && withTranslating.hasNext() && i < 5; i++) {
            finalSentence.append("💬")
                    .append(withoutTranslating.next())
                    .append("\n\r")
                    .append("‍🗨")
                    .append(withTranslating.next())
                    .append("\n\r\n\r");
        }

        return finalSentence.toString();
    }

    private String encodeSpaces(String str) {
        return str.replace(" ", "-");
    }
}