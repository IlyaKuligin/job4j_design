package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> i = set.iterator();
        if (value == null) {
            while (i.hasNext()) {
                if (i.next() == value) {
                    return true;
                }
            }
        } else {
            while (i.hasNext()) {
                if (i.next().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
