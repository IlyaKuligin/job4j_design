package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        mem.replace(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        mem.remove(id);
        return true;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
