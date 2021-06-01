package com.Eragoo.ReversoContextBot.config;

public class DbConfig {
    public static final String URL = "jdbc:postgresql://"+System.getenv("BOT_DB_HOST")+":5432/core";
    public static final String USR = "root";
    public static final String PASS = "root";
}

