package com.Erag0.ReversoContextBot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private long chat_id;
    private String username;
    private String command;
    private String language;
}
