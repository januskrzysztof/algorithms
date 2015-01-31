package com.algorythms.lists;

import com.algorythms.iterators.ArrayIterator;
import com.algorythms.iterators.Iterator;

/**
 * Created by Janek on 2015-01-29.
 */
public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final int initialCapacity;
    transient private Object[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be greater then 0.");
        }
        this.initialCapacity = initialCapacity;
        clear();
    }

    @Override
    public void insert(int index, E value) throws IndexOutOfBoundsException {
        if (value == null) {
            throw new IndexOutOfBoundsException("Value can not be null.");
        }
        if (index > size || index < 0) {
            throw  new IndexOutOfBoundsException("Index can not be greater then array size("+size+") and less then 0.");
        }

        ensureCapacity(size+1);
        System.arraycopy(array, index, array, index+1, size-index);
        array[index] = value;
        ++size;
    }

    @Override
    public void add(E value) {
        insert(size, value);
    }

    @Override
    public void clear() {
        array = new Object[initialCapacity];
        size  = 0;
    }

    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        E e = get(index);

        System.arraycopy(array, index+1, array, index, size-index-1);
        array[--size] = null;

        return e;
    }

    @Override
    public boolean delete(E value) {
        int index = indexOf(value);
        return delete(index).equals(value);
    }

    @Override
    public E set(int index, E value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index can not be less then 0 and greater then array size("+size+")");
        }
        if (value == null) {
            throw new IndexOutOfBoundsException("Value can not be null.");
        }

        E oldValue   = get(index);
        array[index] = value;

        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of array range.");
        }

        return (E) array[index];
    }

    @Override
    public int indexOf(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }

        for (int i=0; i<size; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(array, 0, size);
    }

    private void ensureCapacity(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater then 0.");
        }

        if (array.length < capacity) {
            Object[] copy = new Object[capacity];
            System.arraycopy(array, 0, copy, 0, size);
            array = copy;
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("ArrayList: [");
        for (int i=0; i<size; i++) {
            if (array[i] != null) {
                b.append(array[i].toString());
            } else {
                b.append("null");
            }
            if (i < size-1) {
                b.append(", ");
            }
        }

        return b.append("]").toString();
    }
}