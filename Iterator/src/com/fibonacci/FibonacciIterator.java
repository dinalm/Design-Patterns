/*
 * Design Decision:
 * The Fibonacci calculation state is maintained inside the FibonacciIterator
 * class rather than FibonacciSequence.
 *
 * Rationale:
 * - Each iterator operates independently.
 * - Multiple iterators can traverse the sequence simultaneously without
 *   interfering with each other.
 * - FibonacciSequence acts only as a sequence provider (aggregate).
 * - This follows the Iterator design pattern more cleanly by separating:
 *      1. Sequence definition
 *      2. Iteration state
 *      3. Traversal logic
 *
 * Benefit:
 * If state were stored in FibonacciSequence, all iterators would share
 * progress, causing conflicts and preventing independent traversal.
 */

package com.fibonacci;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {

    private int previous = 1;
    private int current = 1;
    private int count = 0;
    private final int limit;

    /*
     * State is stored here instead of FibonacciSequence so that
     * each iterator instance is independent.
     */
    public FibonacciIterator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return count < limit;
    }

    @Override
    public Integer next() {
        count++;

        // First two Fibonacci numbers are always 1
        if (count == 1 || count == 2) {
            return 1;
        }

        int nextValue = previous + current;
        previous = current;
        current = nextValue;

        return nextValue;
    }
}