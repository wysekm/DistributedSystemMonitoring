package pl.edu.agh.dsm.datastore;

import java.util.LinkedList;

public class CircularBuffer<T> extends LinkedList<T> {

    private int limit;

    public CircularBuffer(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        cleanup();
    }

    private void cleanup() {
        while (size() > limit) {
            super.remove();
        }
    }

    @Override
    public boolean add(T o) {
        boolean added = super.add(o);
        if (added) {
            cleanup();
        }
        return added;
    }
}
