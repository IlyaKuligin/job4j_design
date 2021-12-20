package ru.job4j.io;

import java.io.*;
import java.util.function.Consumer;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .peek(new Consumer<>() {
                        private String prev = "0";

                        @Override
                        public void accept(String s) {
                            if ((s.startsWith("500") || s.startsWith("400"))
                                    && ((!prev.startsWith("500")) && !prev.startsWith("400"))) {
                                out.print(s.split(" ")[1] + ";");
                            }
                            if ((!s.startsWith("500") && !s.startsWith("400"))
                                    && ((prev.startsWith("500")) || prev.startsWith("400"))) {
                                out.println(s.split(" ")[1] + ";");
                            }
                            prev = s;
                        }
                    })
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server/server.log", "./data/server/unavailable.csv");
    }
}
