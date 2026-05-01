package com.fibonacci;

import java.util.Iterator;

public class FibonacciSequence implements Sequence {

    private final int limit;

    // limit determines how many Fibonacci numbers will be generated.
    // Can be adjusted for finite or large sequences.
    public FibonacciSequence(int limit) {
        this.limit = limit;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator(limit);
    }
}