package com.diva.basics.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFast {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        try {
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String elememt = itr.next();
            System.out.println("===>" +elememt);
            list.add("s");
        }
        } catch(Exception e) {
            System.out.println("Not allowed to modify ::" +e);
        } finally{
            list.add("df");
        }
        System.out.println(list);
    }
}
