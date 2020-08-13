package com.Erag0.ReversoContextBot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCommandFromMessage {

    public static boolean isCommand(String str) {
        if (str != null){
            String trimmed = str.trim().toLowerCase();
            Pattern p = Pattern.compile("(/\\S+)");
            Matcher matcher = p.matcher(str);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static String getCommand(String str) {
        String command = "";
        if (str != null){
            String trimmed = str.trim().toLowerCase();
            Pattern p = Pattern.compile("(/\\S+)");
            Matcher matcher = p.matcher(str);
            if (matcher.find()) {
                command = matcher.group(1);
            }
        }
        return command;
    }

}
