package com.Erag0.ReversoContextBot.command;

import lombok.Getter;

@Getter
public enum CommandName {
    START("/start"),
    HELP("/help"),
    PARSE("/parse"),
    SET_LANGUAGE(null);

    private String name;
    CommandName(String name) {
        this.name = name;
    }
}
