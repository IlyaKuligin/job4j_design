package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Требования:
1. Разрешение коллизий реализовывать НЕ нужно. Метод вставки в случае отсутствия места должен возвращать false.
2. Метод get() в случае отсутствия значения должен возвращать null, в противном случае само значение.
3. Метод remove() в случае успешного удаления должен возвращать true, в противном случае false.
4. Итератор должен обладать fail-fast поведением
5. Хэш-таблица должна быть динамической, т.е. расширяться при необходимости.

Задание:
1. Реализовать хэш-функцию (метод  hash()) на ваш выбор
2. Реализовать методы SimpleMap
3. Написать как минимум по 2 теста на каждый публичный метод мапы.
4. Залить код на Github
5. Приложить ссылку и перевести ответственного на Петра Арсентьева
*/

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (MapEntry<K, V> el : this.table) {
            int i = indexFor(hash((el.key == null) ? 0 : el.key.hashCode()));
            if (table[i] == null) {
                table[i] = el;
            }
        }
        this.table = table;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] != null) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int i = indexFor(hash(hashCode));
        if (table[i] != null) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
