package com.algorythms.lists;

import com.algorythms.iterators.Iterable;

/**
 * Created by Janek on 2015-01-28.
 */
public interface List<E> extends Iterable {
    public void insert(int index, E value) throws IndexOutOfBoundsException;
    public void add(E value);
    public void clear();
    public E delete(int index) throws IndexOutOfBoundsException;
    public boolean delete(E value);
    public E set(int index, E value) throws IndexOutOfBoundsException;
    public E get(int index) throws IndexOutOfBoundsException;
    public int indexOf(E value);
    public boolean contains(E value);
    public int size();
    public boolean isEmpty();
}
