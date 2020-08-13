package com.Erag0.ReversoContextBot.logger.Behavior;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogBehavior implements LogBehavior{

    public void write(String mode, String pathToFile, String str) {
        writeToFile(mode, pathToFile, str);
    }

    private void writeToFile(String mode, String pathToFile, String text){
        try(FileWriter writer = new FileWriter(pathToFile, true)){

            if (mode.equals("info")) {
                writer.write(formatDataInfo(text));
                writer.close();
            }else if (mode.equals("error")){
                writer.write(formatDataError(text));
                writer.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private String formatDataInfo(String text) {
        String header = "AT:  " + getDate().toUpperCase();
        String content = "CONTENT: \n" + text;
        String mode = "[INFO]\n";
        String separator = "*********************************************************";

        return mode + header + "\n" + content + "\n\n" + separator + "\n\n";
    }

    private String formatDataError(String text) {
        String header = "AT:  " + getDate().toUpperCase();
        String content = "CONTENT: \n" + text;
        String mode = "[ERROR]\n";
        String separator = "*********************************************************";

        return mode + header + "\n" + content + "\n\n" + separator + "\n\n";
    }



    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }


}