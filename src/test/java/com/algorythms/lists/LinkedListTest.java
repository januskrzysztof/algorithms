package com.algorythms.lists;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class LinkedListTest extends AbstractListTest {
    @Override
    protected List<Object> createList() {
        return new LinkedList<>();
    }
}
