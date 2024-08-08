package com.example.composition;

import java.util.HashSet;

/**
 * A broken version of InstrumentedHashSet that fails to increment the addCount field correctly
 * in a case when elements are added to the set.
 * See <a href="https://blogs.oracle.com/javamagazine/post/java-inheritance-composition">Oracle blog</a>}
 */
public class BrokenInstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    private int addCount = 0;

    public BrokenInstrumentedHashSet() {
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
