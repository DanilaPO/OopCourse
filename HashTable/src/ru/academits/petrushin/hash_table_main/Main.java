package ru.academits.petrushin.hash_table_main;

import ru.academits.petrushin.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>();
        HashTable<Integer> table1 = new HashTable<>();

        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 221;
        Integer x4 = 9999;
        Integer x5 = 999;

        table.add(x1);
        table.add(x2);
        table.add(x3);
        table.add(x4);
        table.add(x5);

        table1.add(x4);
        table1.add(x5);
        table1.add(x3);

        // метод size()
        System.out.println("Размер хэш-блицы пуста: " + table.size());
        System.out.println();

        // метод isEmpty()
        System.out.println("Хэш-блица пуста: " + table.isEmpty());
        System.out.println();

        // метод boolean contains(Object o)
        System.out.println("Наличие элемента в хэш-табице: " + table.contains(x5));
        System.out.println();

        // метод remove(Object o)
        table.remove(x4);
        System.out.println("Хэш-табица с удленным элементом: ");
        System.out.println(table);
        System.out.println();

        // метод clear()
        table.clear();
        System.out.println("Хэш-табица со всеми удленными элементами: ");
        System.out.println(table);
        System.out.println();

        // метод  toArray()
        System.out.println("Преобразование хэш-таблицы в массив: " + Arrays.toString(table.toArray()));
        System.out.println();

        // метод toArray(E[] a)
        Integer[] array = new Integer[0];
        System.out.println("Заполнение массива данными хэш-таблицы: " + Arrays.toString(table.toArray(array)));
        System.out.println();

        // метод containsAll(Collection<?> c)
        table.containsAll(table1);
        System.out.println("Наличие элементов коллекции в хэш-таблице: " + table.containsAll(table1));

        // метод addAll(Collection<? extends E> c)
        table.addAll(table1);
        System.out.println("Хэш-таблица с перенесенными в нее элементами из другой коллекции: ");
        System.out.println(table);

        // метод removeAll(Collection<?> c)
        table.removeAll(table1);
        System.out.println("Хэш-таблица с удаленными элементами, принадлежащими переданной коллекции: " + table);

        // метод retainAll(Collection<?> c)
        table.retainAll(table1);
        System.out.println("Хэш-блица без элементов, отсутсвующих в переданной коллекции:");
        System.out.println(table);
    }
}