package com.algorythms.iterators;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Janek on 2015-01-27.
 */
public class FilterIteratorTest {
    private static final String[] o = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    private FilterIterator sut;

    class PredicateEveryOther implements Predicate {
        private int iter = -1;

        @Override
        public boolean evaluate(Object obj) {
            return iter++%2 == 0;
        }
    }

    class PredicateWithTwoPredicates implements Predicate {
        private Predicate predicate1;
        private Predicate predicate2;

        public PredicateWithTwoPredicates(Predicate predicate1, Predicate predicate2) {
            this.predicate1 = predicate1;
            this.predicate2 = predicate2;
        }

        @Override
        public boolean evaluate(Object obj) {
            return predicate1.evaluate(obj) && predicate2.evaluate(obj);
        }
    }

    @Test
    public void testPredicateWithOnlyAElement() {
        Predicate predicate = obj -> obj.toString().equals(o[0]);

        sut = new FilterIterator(new ArrayIterator(o), predicate);

        assertEquals(o[0], sut.current());
        assertFalse(sut.isDone());
    }

    @Test
    public void testPredicateWithTwoGElements() {
        sut = new FilterIterator(new ArrayIterator(o), obj -> obj.toString().equals(o[0]) || obj.toString().equals(o[6]));

        assertEquals(o[0], sut.current());
        assertFalse(sut.isDone());

        sut.next();
        assertEquals(o[6], sut.current());
        assertFalse(sut.isDone());

        sut.next();
        assertTrue(sut.isDone());
        try {
            sut.current();
            fail();
        } catch (Exception ex) {
            assertEquals(ArrayIndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testPredicateEveryOtherElements() {
        sut = new FilterIterator(new ArrayIterator(o), new PredicateEveryOther());

        int i;
        for(sut.first(), i=0; !sut.isDone(); sut.next(), i+=2) {
            assertEquals(o[i], sut.current());
        }

        for (sut.last(), i=o.length-1; !sut.isDone(); sut.previous(), i-=2) {
            assertEquals(o[i], sut.current());
        }
    }

    @Test
    public void testPredicateWithTwoPredicates() {
        Predicate predicate = new PredicateWithTwoPredicates(
                new PredicateEveryOther(),
                obj -> obj.equals("A") || obj.equals("K")
        );

        sut = new FilterIterator(new ArrayIterator(o), predicate);
        Iterator iterator = new ArrayIterator(new String[] {"A", "K"});

        for (iterator.first(), sut.first(); !sut.isDone() && !iterator.isDone(); iterator.next(), sut.next()) {
            assertEquals(iterator.current(), sut.current());
        }
    }
}
