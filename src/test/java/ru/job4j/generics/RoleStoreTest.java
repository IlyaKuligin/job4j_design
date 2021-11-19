package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenReplaceTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1"));
        Assert.assertTrue(roleStore.replace("1", new Role("4")));
    }

    @Test
    public void whenReplaceFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1"));
        Assert.assertFalse(roleStore.replace("2", new Role("4")));
    }

    @Test
    public void whenDeleteTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1"));
        Assert.assertTrue(roleStore.delete("1"));
    }

    @Test
    public void whenDeleteFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1"));
        Assert.assertFalse(roleStore.delete("2"));
    }
}