package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {
    public static PrintWriter out;

    private static String historyByLogin (String login){
        return "myChatHistory/history_" + login + ".txt";
    }

    public static void start(String login){
        try {
            out = new PrintWriter(new FileOutputStream(historyByLogin(login), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        if (out != null){
            out.close();
        }
    }

    public static void writeInLine(String text){
        out.println(text);
    }

    public static String collectionHistory(String login){
        StringBuilder sb = new StringBuilder();
        try {
            List<String> collectHistory = Files.readAllLines(Paths.get(historyByLogin(login)));
            int startingPosition = 0;
            if (collectHistory.size() > 100) {
                startingPosition = collectHistory.size() - 100;
            }
            for (int i = startingPosition; i < collectHistory.size(); i++) {
                sb.append(collectHistory.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
