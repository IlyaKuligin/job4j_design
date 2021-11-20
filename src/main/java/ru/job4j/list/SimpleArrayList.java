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
            grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        T val = get(index);
        container[index] = newValue;
        return val;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T val = get(index);
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

    private void grow() {
        if (container.length == 0) {
            container = (T[]) new Object[1];
        } else {
            container = Arrays.copyOf(container, container.length * INCREMENT_ARR);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
