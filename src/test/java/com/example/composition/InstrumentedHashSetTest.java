package com.example.composition;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentedHashSetTest {

    @Test
    void add() {
        InstrumentedHashSet<Integer> set = new InstrumentedHashSet<>(new HashSet<>());
        set.add(1);
        set.add(2);
        set.add(3);
        assertEquals(3, set.getAddCount());
    }

    @Test
    void addAll() {
        InstrumentedHashSet<Integer> set = new InstrumentedHashSet<>(new HashSet<>());
        set.addAll(List.of(1, 2, 3));

        // Works as expected
        assertEquals(3, set.getAddCount());
    }

}