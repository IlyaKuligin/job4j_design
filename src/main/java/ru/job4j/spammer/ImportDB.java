package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(el -> {
                String[] elements = el.split(";");
                if (elements.length != 2) {
                    throw new IllegalArgumentException("Некорректное число аргументов в файле");
                }
                users.add(new User(elements[0], elements[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (Statement statement = cnt.createStatement()) {
                statement.execute(String.format(
                        "create table if not exists %s(%s, %s, %s);",
                        "spammers",
                        "id serial primary key",
                        "name varchar(255)",
                        "email varchar(255)")
                );
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка доступа к БД: " + e);
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into spammers(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }

}
