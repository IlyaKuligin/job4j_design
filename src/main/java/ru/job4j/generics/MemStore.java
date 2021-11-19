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
        boolean rsl = false;
        try {
            rsl = mem.get(id).equals(mem.replace(id, model));
        } catch (NullPointerException | ClassCastException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try {
            rsl = mem.get(id).equals(mem.remove(id));
        } catch (NullPointerException | ClassCastException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
