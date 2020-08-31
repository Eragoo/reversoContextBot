package com.Erag0.ReversoContextBot.logger;

import com.Erag0.ReversoContextBot.logger.behavior.LogBehavior;
import com.Erag0.ReversoContextBot.logger.behavior.SimpleLogBehavior;

public class Logger {
    private static LogBehavior logBehavior = new SimpleLogBehavior();

    public static void Log(String mode, String pathToFile, Object obj) {
        String string = obj.toString();
        logBehavior.write(mode, pathToFile, string);
    }

    public static void setBehavior(LogBehavior b) {
        logBehavior = b;
    }
}