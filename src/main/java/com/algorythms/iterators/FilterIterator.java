package com.algorythms.iterators;

/**
 * Created by Janek on 2015-01-27.
 */
public class FilterIterator implements Iterator {
    private final Predicate predicate;
    private final Iterator iterator;

    public FilterIterator(Iterator iterator, Predicate predicate) {
        this.iterator  = iterator;
        this.predicate = predicate;
    }

    @Override
    public void first() {
        iterator.first();
    }

    @Override
    public void last() {
        iterator.last();
    }

    @Override
    public void next() {
        do {
            iterator.next();
        } while (!isDone() && !predicate.evaluate(iterator.current()));
    }

    @Override
    public void previous() {
        do {
            iterator.previous();
        } while (!isDone() && !predicate.evaluate(iterator.current()));
    }

    @Override
    public boolean isDone() {
        return iterator.isDone();
    }

    @Override
    public Object current() throws ArrayIndexOutOfBoundsException {
        if (isDone()) {
            throw new ArrayIndexOutOfBoundsException("Has no more elements");
        }
        return iterator.current();
    }
}
