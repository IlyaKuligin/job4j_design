package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private static final int INCREMENT_ARR = 2;
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * INCREMENT_ARR);
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        Objects.checkIndex(index, size);
        T val = container[index];
        container[index] = newValue;
        return val;
    }

    @Override
    public T remove(int index) {
        modCount++;
        Objects.checkIndex(index, size);
        T val = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        container[container.length - 1] = null;
        return val;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return container[point++];
            }
        };
    }
}
