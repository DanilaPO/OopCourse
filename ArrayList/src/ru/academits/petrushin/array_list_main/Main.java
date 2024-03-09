package ru.academits.petrushin.array_list_main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
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
        Integer y3 = 11;
        Integer y4 = 20;
        Integer y5 = 47;

        list2.add(y3);
        list2.add(y4);
        list2.add(y5);

        // Удаление элемента remove
        System.out.println("Список до удаления элемента: " + list);
        System.out.println("Удаление элемента по индексу. Удален элемент: " + list.remove(0));
        System.out.println();

        // Метод containsAll (проверка наличия элементов коллекций)
        System.out.println("Список содержит элементы другой коллекции: " + list.containsAll(list2));
        System.out.println();

        // Метод добавления всех элементов коллекции в список - addAll
        System.out.println("Список до добавления всех элементов коллекции в список: " + list);
        System.out.println(list.addAll(list2));
        System.out.println("Результат добавления всех элементов коллекции в список: " + list);
        System.out.println();

        // Метод добавления всех элементов коллекции в список по индексу - addAll
        System.out.println("Список до добавления всех элементов коллекции в список по индексу: " + list);
        list.addAll(5, list2);
        System.out.println("Результат добавления всех элементов коллекции в список: " + list);
        System.out.println();

        // Метод удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции - removeAll
        System.out.println("Список до удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции: " + list);
        list.removeAll(list2);
        System.out.println("Результат удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции: " + list);
        System.out.println();

        // Метод сохранения в этой коллекции только тех элементов, которые содержатся в указанной коллекции - retainAll
        System.out.println("Список до сохранения в этой коллекции только тех элементов, которые содержатся в указанной коллекции: " + list);
        list.retainAll(list2);
        System.out.println("Результат сохранения всех элементов, которые содержатся в указанной коллекции: " + list);
        System.out.println();

        // Метод очистки списка - clear()
        System.out.println("Список до очистки списка: " + list);
        list.clear();
        System.out.println("Результат очистки списка: " + list);
        System.out.println();

        // Метод получения элемента списка по индексу - get
        System.out.println("Результат получения элемента по индексу: " + list2.get(1));
        System.out.println();

        // Замена элемента списка по индексу - set
        System.out.println("Список до замены элемента списка по индексу: " + list2);
        list2.set(2, 99);
        System.out.println("Результат замены элемента по индексу: " + list2);
        System.out.println();

        // Метод вставляет указанный элемент в указанную позицию в этом списке - add(int index, T element)
        System.out.println("Список до звставки указанного элемента в указанную позицию в этом списке: " + list2);
        list2.add(2, 99);
        System.out.println("Результат вставки элемента по индексу: " + list2);
        System.out.println();

        // Удаление элемента по индексу - remove
        System.out.println("Список до удаления элемента по индексу: " + list2);
        list2.remove(1);
        System.out.println("Результат удаления элемента по индексу: " + list2);
        System.out.println();

        // Метод возвращает индекс первого вхождения указанного элемента в этом списке - indexOf
        System.out.println("Индекс первого вхождения указанного элемента: " + list.indexOf(null));
        System.out.println();

        // Метод возвращает индекс последнего вхождения указанного элемента в этом векторе - lastIndexOf()
        System.out.println("Индекс последнего вхождения указанного элемента: " + list.lastIndexOf(null));
        System.out.println();

        // Метод contains(Object o) возвращает true, если этот набор содержит элемент
        System.out.println("Нахождение элемента в списке: " + list.contains(11));
    }
}