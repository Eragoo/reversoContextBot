package com.Eragoo.ReversoContextBot.db;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAction {
    private long chatId;
    private String username;
    private String command;
    private String lang;
}
