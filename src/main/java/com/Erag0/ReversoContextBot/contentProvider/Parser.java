package com.Erag0.ReversoContextBot.contentProvider;

import java.io.IOException;

import com.Erag0.ReversoContextBot.bot.callback.Language;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import java.util.logging.Level;

@Log
public class Parser {
    public String parse(Language lang, String phrase) throws IOException {
        phrase = encodeSpaces(phrase.trim());
        Document doc = Jsoup.connect("https://context.reverso.net/translation/" + lang + "/" + phrase).get();

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