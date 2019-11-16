package com.Erag0.ReversoContextBot.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCommandFromMessage {
    private static String command;

    public static boolean isCommand(String str) {
        String trimmed = str.trim().toLowerCase();
        Pattern p = Pattern.compile("(/\\S+)");
        Matcher matcher = p.matcher(str);

        if (matcher.find()) {
            command = matcher.group(1);
            return true;
        }
        return false;
    }

    public static String getCommand() {
        return command;
    }
}
