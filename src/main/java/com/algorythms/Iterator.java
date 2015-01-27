package com.algorythms;

/**
 * Created by Janek on 2015-01-26.
 */
public interface Iterator {
    public void first();
    public void last();
    public void next();
    public void previous();
    public boolean isDone();
    public Object current() throws ArrayIndexOutOfBoundsException;
}
