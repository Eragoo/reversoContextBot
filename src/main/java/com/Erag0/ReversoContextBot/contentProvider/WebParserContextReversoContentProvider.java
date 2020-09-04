package com.Erag0.ReversoContextBot.contentProvider;

import com.Erag0.ReversoContextBot.bot.callback.Language;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.logging.Level;

@Log
public class WebParserContextReversoContentProvider implements ContextReversoContentProvider {
    @Override
    public String getContent(Language language, String phrase) {
        Parser parser = new Parser();
        String content;
        try {
            content = parser.parse(language, phrase);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
            content = ("__К сожалению этой фразы нет в нашей базе__😞");
        }
        return content;
    }
}
