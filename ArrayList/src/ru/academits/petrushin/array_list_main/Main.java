package ru.academits.petrushin.array_list_main;

import ru.academits.petrushin.array_list.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list =  new ArrayList<>();
        Integer x = 0;
        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 3;
        Integer x4 = 4;
        Integer x5 = 5;
        Integer x6 = 6;
        Integer x7 = 7;
        Integer x8 = 8;
        Integer x9 = 9;
        Integer x10 = 10;

        list.add(x);
        list.add(x1);
        list.add(x2);
        list.add(x3);
        list.add(x4);
        list.add(x5);
        list.add(x6);
        list.add(x7);
        list.add(x8);
        list.add(x9);
        list.add(x10);

        List<Integer> list2 = new LinkedList<>();
        list2.add(x3);
        list2.add(x4);
        list2.add(x5);

        list.toArray(list.toArray());

        System.out.println(Arrays.toString(list.toArray(list.toArray())));
    }
}