package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {

    private final Collection<T> collection;
    private int index;

    protected BackwardIterator(Collection<T> collection) {
        if (collection != null) {
            this.collection = collection;
            index = collection.size() - 1;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        T[] arrayCollection = (T[]) new Object[collection.size()];
        collection.toArray(arrayCollection);
        return arrayCollection[index--];
    }
}
