package ru.academits.petrushin.hash_table_main;

import ru.academits.petrushin.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();
        HashTable<Integer> table2 = new HashTable<>();

        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 3;
        Integer x4 = 9999;
        Integer x5 = 999;

        table1.add(x1);
        table1.add(x2);
        table1.add(x3);
        table1.add(x4);
        table1.add(x5);

        table2.add(x1);
        table2.add(x3);
        table2.add(x4);
        table2.add(x5);

        // метод size()
        System.out.println("Количество элементов хэш-таблицы: " + table1.size());
        System.out.println();

        // метод isEmpty()
        System.out.println("Хэш-таблица пуста: " + table1.isEmpty());
        System.out.println();

        // метод boolean contains(Object o)
        System.out.println("Наличие элемента в хэш-таблице: " + table1.contains(null));
        System.out.println();

        // метод remove(Object o)
        table1.remove(x5);
        System.out.println("Хэш-таблица с удаленным элементом:");
        System.out.println(table1);
        System.out.println();

        // метод clear()
        table1.clear();
        System.out.println("Хэш-таблица со всеми удаленными элементами:");
        System.out.println(table1);
        System.out.println();

        // метод  toArray()
        System.out.println("Преобразование хэш-таблицы в массив: " + Arrays.toString(table1.toArray()));
        System.out.println();

        // метод toArray(T[] a)
        Integer[] array = new Integer[7];
        System.out.println("Заполнение массива данными хэш-таблицы: " + Arrays.toString(table1.toArray(array)));
        System.out.println();

        // метод containsAll(Collection<?> c)
        System.out.println("Наличие элементов коллекции в хэш-таблице: " + table1.containsAll(table2));
        System.out.println();

        // метод addAll(Collection<? extends E> c)
        table1.addAll(table2);
        System.out.println("Хэш-таблица с перенесенными в нее элементами из другой коллекции:");
        System.out.println(table1);
        System.out.println();

        // метод removeAll(Collection<?> c)
        table1.removeAll(table2);
        System.out.println("Хэш-таблица с удаленными элементами, принадлежащими переданной коллекции:");
        System.out.println(table1);
        System.out.println();

        // метод retainAll(Collection<?> c)
        table1.retainAll(table2);
        System.out.println("Хэш-таблица без элементов, отсутствующих в переданной коллекции:");
        System.out.println(table1);
    }
}