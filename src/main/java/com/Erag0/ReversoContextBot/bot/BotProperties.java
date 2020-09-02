package com.Erag0.ReversoContextBot.bot;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class BotProperties {
    public static PropertiesConfiguration config;

    static {
        try {
            config = new PropertiesConfiguration("application.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    public static String TOKEN = config.getString("bot.token");
}
