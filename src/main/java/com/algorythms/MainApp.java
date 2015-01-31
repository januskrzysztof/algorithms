package com.algorythms;

import com.algorythms.lists.ArrayList;
import com.algorythms.lists.List;

/**
 * Created by Janek on 2015-01-29.
 */
public class MainApp {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>(2);

        list.insert(0, "asd");
        list.insert(1, "dsa");
        list.insert(2, "xxx");
        list.add("x");
        System.out.println(list);

        list.delete(1);
        System.out.println(list);

        Object o = list.set(1, "dupa");
        System.out.println(o.toString());
        System.out.println(list);

        System.out.println(list.delete(0));
        System.out.println(list);
    }
}
