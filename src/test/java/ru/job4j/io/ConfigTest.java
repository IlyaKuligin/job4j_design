package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        /*assertThat(config.value("surname"), is(Matchers.nullValue()));*/
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIllegalArgumentException() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWitComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPairWitEmptyString() {
        String path = "./data/pair_with_empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenVeryIncorrectFile() {
        String path = "./data/very_incorrect_file.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("=jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }
}