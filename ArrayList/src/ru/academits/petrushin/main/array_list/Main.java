package ru.academits.petrushin.main.array_list;


import ru.academits.petrushin.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
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

        Integer y = 10;

        System.out.println(list.contains(10));

    }
}