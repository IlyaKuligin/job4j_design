package ru.job4j.io.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        loadPhrases();
        List<String> phrases = readPhrases();
        int exitStatus = 0;
        while (exitStatus != 1) {
            String s = input.nextLine();
            log.add(s);
            if (STOP.equals(s)) {
                int stopStatus = 0;
                while (stopStatus != 1) {
                    s = input.nextLine();
                    if (!CONTINUE.equals(s) && !OUT.equals(s)) {
                        log.add(s);
                    } else if (OUT.equals(s)) {
                        break;
                    } else {
                        log.add(s);
                        stopStatus = 1;
                    }
                }
            }
            if (!OUT.equals(s)) {
                String botAnswer = phrases.get((int) (Math.random() * (phrases.size())));
                System.out.println(botAnswer);
                log.add(botAnswer);
            } else {
                exitStatus = 1;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(el -> phrases.add(el));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPhrases() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                botAnswers, Charset.forName("UTF-8"), true))) {
            List<String> phrases = List.of(
                    "Привет!",
                    "Как дела?",
                    "Пока!",
                    "Случайная фраза.",
                    "Еще одна случайня фраза."
            );
            phrases.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/chatLog.txt", "./src/data/botAnswers.txt");
        cc.run();
    }
}
