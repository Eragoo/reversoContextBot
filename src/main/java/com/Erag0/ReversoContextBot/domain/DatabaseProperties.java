package com.Erag0.ReversoContextBot.domain;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DatabaseProperties {
    public static PropertiesConfiguration config;

    static {
        try {
            config = new PropertiesConfiguration("application.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    public static String URL=config.getString("database.url");
}
