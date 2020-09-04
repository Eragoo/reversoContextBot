package com.Erag0.ReversoContextBot.contentProvider;

import com.Erag0.ReversoContextBot.bot.callback.Language;

public interface ContextReversoContentProvider {
    String getContent(Language language, String phrase);
}
