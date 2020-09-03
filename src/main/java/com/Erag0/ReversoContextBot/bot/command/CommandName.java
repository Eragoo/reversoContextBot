package com.Erag0.ReversoContextBot.bot.command;

import lombok.Getter;

@Getter
public enum CommandName {
    START("/start"),
    HELP("/help"),
    PARSE("/parse");

    private String name;
    CommandName(String name) {
        this.name = name;
    }
}
