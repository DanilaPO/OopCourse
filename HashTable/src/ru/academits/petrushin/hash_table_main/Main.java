package ru.academits.petrushin.hash_table_main;

import ru.academits.petrushin.hash_table.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>();

        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 221;
        Integer x4 = 9999;

        table.add(x1);
        table.add(x2);
        table.add(x3);
        table.add(x4);

        table.print();
    }
}