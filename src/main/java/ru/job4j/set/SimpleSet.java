package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> i = set.iterator();
        if (value == null) {
            while (i.hasNext()) {
                if (i.next() == value) {
                    rsl = true;
                }
            }
        } else {
            while (i.hasNext()) {
                if (i.next().equals(value)) {
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
