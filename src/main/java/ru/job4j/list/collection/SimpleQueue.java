package ru.job4j.list.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    public T poll() {
        int count = this.count;
        T rsl;
        while (this.count != 0) {
            out.push(in.pop());
            this.count--;
        }
        rsl = out.pop();
        while (this.count != count - 1) {
            in.push(out.pop());
            this.count++;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}
