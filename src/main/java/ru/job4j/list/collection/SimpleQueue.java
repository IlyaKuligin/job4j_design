package ru.job4j.list.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl;
        while (in.hasNext()) {
            out.push(in.pop());
        }
        rsl = out.pop();
        while (out.hasNext()) {
            in.push(out.pop());
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }
}
