package com.Eragoo.ReversoContextBot.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class WebParser {
    public static List<ParseResult> getTranslation(String lang, String word) throws IOException {
        return processData(getDataFromHtml(lang, word));
    }

    private static HashMap<String, Elements> getDataFromHtml(String lang, String word) throws IOException {
        HashMap<String, Elements> elements = new HashMap<>();
        Document doc = Jsoup.connect("https://context.reverso.net/translation/" + lang + "/" + word).get();
        Elements translate = doc.select("div#translations-content");
        Elements nativeSentence = doc.select("div.src.ltr");
        Elements translatedSentence = doc.select("div.trg.ltr");
        elements.put("translate", translate);
        elements.put("examplesSource", nativeSentence);
        elements.put("examplesTranslated", translatedSentence);

        return elements;
    }

    private static List<ParseResult> processData(HashMap<String, Elements> elems) {
        List<ParseResult> results = new ArrayList<>();

        Iterator<Element> sourceIteratorSentence = elems.get("examplesSource").iterator();
        Iterator<Element> translatedIteratorSentence = elems.get("examplesTranslated").iterator();
        int i = 0;
        while (sourceIteratorSentence.hasNext() && translatedIteratorSentence.hasNext()) {
            if (i > 5) {
                break;
            }
            String sourceExapleText = sourceIteratorSentence.next().text();
            String translatedExampleText = translatedIteratorSentence.next().text();

            results.add(new ParseResult(sourceExapleText, translatedExampleText));

            i++;
        }

        return results;
    }
}
