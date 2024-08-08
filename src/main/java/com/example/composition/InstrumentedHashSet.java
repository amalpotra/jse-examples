package com.example.composition;

import java.util.Collection;
import java.util.Set;

// Wrapper class (or Decorator pattern) - uses composition in place of inheritance
public class InstrumentedHashSet<E> extends ForwadingSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
