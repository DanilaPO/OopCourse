package ru.academits.petrushin.array_list_main;

import ru.academits.petrushin.array_list.ArrayList;

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
        Integer y3 = 11;
        Integer y4 = 2;
        Integer y5 = 3;

        list2.add(y3);
//        list2.add(y4);
//        list2.add(y5);

//        // Удаление элемента remove
//        System.out.println("Удаление элемента по индексу. Удален элемент: " + list.remove(10));
//
//        // Метод containsAll (проверка наличия эелементов коллекций)
//        System.out.println("Список содржит элементы другой коллекции: " + list.containsAll(list2));

//        // Метод добавления всех элементов коллекции в список - addAll
//        list.addAll(list2);
//        System.out.println("Результат добавления всех элементов коллекции в список: " + list);
//
//        // Метод добавления всех элементов коллекции в список по индексу - addAll
//        list.addAll(12, list2);
//        System.out.println("Результат добавления всех элементов коллекции в список: " + list);
//
//        // Метод удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции - removeAll
//        list.removeAll(list2);
//        System.out.println("Результат удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции: " + list);
//
//        // Метод сохранения в этой коллекции только тех элементов, которые содержатся в указанной коллекции - retainAll
//        list.retainAll(list2);
//        System.out.println("Результат сохранения всех элементов, которые содержатся в указанной коллекции: " + list);
//
//        // Метод очистки списка - clear()
//        list.clear();
//        System.out.println("Результат очистки списка: " + list);
//
//        // Метод получения элемента списка по индексу - get
//        System.out.println("Результат получения элемента по индексу: " + list.get(0));
//
//        // Замена элемента списка по индексу - set
//        list.set(11, 99);
//        System.out.println("Результат замены элемента по индексу: " + list);
//
//        // Метод вставляет указанный элемент в указанную позицию в этом списке - add(int index, T element)
//        list.add(11, 99);
//        System.out.println("Результат вставки элемента по индексу: " + list);
//
//        // Удаление элемента по индексу - remove
//        list.remove(11);
//        System.out.println("Результат удаления элемента по индексу: " + list);
//
//        // Метод возвращает индекс первого вхождения указанного элемента в этом списке - indexOf
//        System.out.println("Индекс первого вхождения указанного элемента: " + list.indexOf(10));
//
//        // Метод возвращает индекс последнего вхождения указанного элемента в этом векторе - lastIndexOf()
//        System.out.println("Индекс последнего вхождения указанного элемента: " + list.lastIndexOf(10));
//
//        // Метод contains(Object o) возвращает true, если этот набор содержит элемент
//        System.out.println("Нахождение элемента в списке: " + list.contains(1));
    }
}