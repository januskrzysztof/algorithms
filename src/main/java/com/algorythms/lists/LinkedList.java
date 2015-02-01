package com.algorythms.lists;

import com.algorythms.iterators.Iterator;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class LinkedList<E> implements List<E> {
    private int size;
    private final Element<E> headAndTail = new Element<>(null);

    public LinkedList() {
        clear();
    }

    @Override
    public void insert(int index, E value) throws IndexOutOfBoundsException {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null value");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Element<E> element = new Element<>(value);
        element.attachBefore(getElement(index));
        ++size;
    }

    private Element getElement(int index) {
        Element element = headAndTail.getNext();
        for (int i=index; i>0; --i) {
            element = element.getNext();
        }

        return element;
    }

    @Override
    public void add(E value) {
        insert(size, value);
    }

    @Override
    public void clear() {
        headAndTail.setNext(headAndTail);
        headAndTail.setPrevious(headAndTail);
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Element element = getElement(index);
        element.detach();
        --size;

        return (E) element.getValue();
    }

    @Override
    public boolean delete(E value) {
        int index = indexOf(value);
        if (index >= 0) {
            delete(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (value == null) {
            throw new IllegalArgumentException();
        }

        Element element = getElement(index);
        insert(index, value);

        return (E) element.getValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (E) getElement(index).getValue();
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        for (Element<E> e = headAndTail.getNext(); e != headAndTail; e = e.getNext()) {
            if (value.equals(e.getValue())) {
                return index;
            }
            ++index;
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
        return new LinkedIterator<>(headAndTail);
    }

    @Override
    @SuppressWarnings("uncheced")
    public String toString() {
        StringBuilder b = new StringBuilder("LinkedList: [");

        Element element = headAndTail.getNext();
        for (int i=0; i<size; i++, element = element.getNext()) {
            b.append(element.getValue());
            if (i < size-1) {
                b.append(",");
            }
        }

        return b.append("]").toString();
    }

    private static final class LinkedIterator<E> implements Iterator {
        private Element headAndTail;
        private Element current;

        public LinkedIterator(Element headAndTail) {
            this.headAndTail = headAndTail;
        }

        @Override
        public void first() {
            current = headAndTail.getNext();
        }

        @Override
        public void last() {
            current = headAndTail.getPrevious();
        }

        @Override
        public void next() {
            current = current.getNext();
        }

        @Override
        public void previous() {
            current = current.getPrevious();
        }

        @Override
        public boolean isDone() {
            return current == null || current.equals(headAndTail);
        }

        @Override
        public Object current() throws ArrayIndexOutOfBoundsException {
            if (!isDone()) {
                return current.getValue();
            }
            throw new IndexOutOfBoundsException();
        }
    }

    private static final class Element<E> {
        private E value;
        private Element previous;
        private Element next;

        public Element(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Element getPrevious() {
            return previous;
        }

        public void setPrevious(Element previous) {
            this.previous = previous;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public void attachBefore(Element next) {
            Element previous = next.getPrevious();

            setNext(next);
            setPrevious(previous);

            next.setPrevious(this);
            previous.setNext(this);
        }

        public void detach() {
            previous.setNext(next);
            next.setPrevious(previous);
        }
    }
}
