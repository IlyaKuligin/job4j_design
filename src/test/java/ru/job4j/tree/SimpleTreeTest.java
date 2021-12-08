package ru.job4j.tree;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
        assertFalse(tree.add(4, 3));
    }

    @Test
    public void when() {
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Person person = (Person) o;
                return age == person.age && Objects.equals(name, person.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }
        Tree<Person> tree = new SimpleTree<>(new Person("Ivan", 30));
        tree.add(new Person("Ivan", 30), new Person("Anton", 40));
        tree.add(new Person("Anton", 40), new Person("Andrey", 20));
        assertTrue(tree.findBy(new Person("Anton", 40)).isPresent());
        assertFalse(tree.findBy(new Person("Anton", 39)).isPresent());
    }
}