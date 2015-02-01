package com.algorythms;

import com.algorythms.lists.LinkedList;

/**
 * Created by Janek on 2015-01-29.
 */
public class MainApp {
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();

        list.add("asd");
        list.add("dsa");
        list.insert(0, "a");
        list.insert(3, "b");
        System.out.println(list);
    }
}
