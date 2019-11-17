package com.Erag0.ReversoContextBot.Logger;

import com.Erag0.ReversoContextBot.Logger.Behavior.LogBehavior;
import com.Erag0.ReversoContextBot.Logger.Behavior.SimpleLogBehavior;

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