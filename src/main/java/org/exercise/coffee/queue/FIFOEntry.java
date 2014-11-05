package org.exercise.coffee.queue;

class FIFOEntry<E extends Comparable<? super E>> implements Comparable<FIFOEntry<E>> {

    private final long timeStamp;
    private final E entry;

    public FIFOEntry(E entry) {
        timeStamp = System.nanoTime();
        this.entry = entry;
    }

    public E getEntry() {
        return entry;
    }

    public int compareTo(FIFOEntry<E> other) {
        int res = entry.compareTo(other.entry);

        if (res == 0 && other.entry != this.entry) {
            res = (timeStamp < other.timeStamp ? -1 : 1);
        }

        return res;
    }
}
