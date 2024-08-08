package com.example.composition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrokenInstrumentedHashSetTest {
    @Test
    void testAdd() {
        BrokenInstrumentedHashSet<Integer> set = new BrokenInstrumentedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        assertEquals(3, set.getAddCount());
    }

    @Test
    void testAddAll() {
        BrokenInstrumentedHashSet<Integer> set = new BrokenInstrumentedHashSet<>();
        set.addAll(java.util.List.of(1, 2, 3));

        /* What went wrong?
         * Internally, the HashSet addAll method is implemented on top of its add method, although HashSet,
         * quite reasonably, does not document this implementation detail.
         * The addAll method in InstrumentedHashSet added three to addCount and then invoked the HashSet addAll
         * implementation using super.addAll.
         * This in turn invoked the add method, as overridden in InstrumentedHashSet, once for each element.
         * Each of these three invocations added one more to addCount, for a total increase of six:
         * Each element added with the addAll method is counted twice.
         */
        assertEquals(6, set.getAddCount());
    }
}
