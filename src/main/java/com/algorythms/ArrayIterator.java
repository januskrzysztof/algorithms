package com.algorythms;

/**
 * Created by Janek on 2015-01-26.
 */
public class ArrayIterator implements Iterator {
    private final Object[] objects;
    private final int first;
    private final int last;
    private int current;

    public ArrayIterator(Object[] objects) {
        this(objects, 0);
    }

    public ArrayIterator(Object[] objects, int start) {
        this(objects, start, objects.length-start);
    }

    public ArrayIterator(Object[] objects, int start, int length) {
        if (start < 0) {
            throw new IllegalArgumentException("Start index can not be less then 0!");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length value can not be less then 0!");
        }
        if (length > objects.length-start) {
            throw new IllegalArgumentException("Length value can not be greater then array length");
        }

        this.objects = objects;
        this.current = start;
        this.first   = start;
        this.last    = start+length-1;
    }

    @Override
    public void first() {
        current = first;
    }

    @Override
    public void last() {
        current = last;
    }

    @Override
    public void next() {
        ++current;
    }

    @Override
    public void previous() {
        --current;
    }

    @Override
    public boolean isDone() {
        return current < first || current > last;
    }

    @Override
    public Object current() throws ArrayIndexOutOfBoundsException {
        return objects[current];
    }
}