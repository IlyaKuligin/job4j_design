package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {

    @Test
    public void whenPutThenTrue() {
        Map<String, String> map = new SimpleMap<>();
        Assert.assertTrue(map.put("Semen", "Semenov, Semen, Semenovich"));
    }

    @Test
    public void whenPutNullThenTrue() {
        Map<String, String> map = new SimpleMap<>();
        Assert.assertTrue(map.put(null, "Hello"));
    }

    @Test
    public void whenPutThenFalse() {
        Map<String, String> map = new SimpleMap<>();
        map.put(null, "Hello");
        Assert.assertFalse(map.put(null, "Bye"));
    }

    @Test
    public void whenGetThenSemen() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Semen", "Semenov, Semen, Semenovich");
        assertThat(map.get("Semen"), is("Semenov, Semen, Semenovich"));
    }

    @Test
    public void whenGetThenHello() {
        Map<String, String> map = new SimpleMap<>();
        map.put(null, "Hello");
        assertThat(map.get(null), is("Hello"));
    }

    @Test
    public void whenGetThenNull() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Semen", "Semenov, Semen, Semenovich");
        Assert.assertNull(map.get("Ivan"));
    }

    @Test
    public void whenRemoveThenTrue() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Semen", "Semenov, Semen, Semenovich");
        Assert.assertTrue(map.remove("Semen"));
    }

    @Test
    public void whenRemoveThenFalse() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Semen", "Semenov, Semen, Semenovich");
        Assert.assertFalse(map.remove("Ivan"));
    }

    @Test
    public void whenCheckIterator() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Semen", "Semenov, Semen, Semenovich");
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next(), "Semen");

    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Map<String, String> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        map.put("Semen", "Semenov, Semen, Semenovich");
        iterator.next();
    }
}