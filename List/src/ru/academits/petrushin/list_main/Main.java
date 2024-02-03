package ru.academits.petrushin.list_main;

import ru.academits.petrushin.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 3;
        Integer x4 = 4;

        list.addFirst(0);
        list.addFirst(x1);
        list.addFirst(x2);
        list.addFirst(x3);
        list.addFirst(x4);

        // получение размера списка
        System.out.println("Размер списка: " + list.getSize());

        // получение значение первого элемента
        System.out.println("Первый элемент списка: " + list.getFirst());

        // получение значения по указанному индексу
        System.out.println("Получение значения элемента по индексу: " + list.get(1));

        // изменение значения по указанному индексу - пусть выдает старое значение
        System.out.println("Прежнее значение замененного элемента: " + list.set(0, 99));
        System.out.println(list);

        // удаление элемента по индексу, пусть выдает значение элемента
        System.out.println("Прежнее значение удаленного элемента: " + list.removeByIndex(0));
        System.out.println(list);

        // вставка элемента в начало
        list.addFirst(8);
        System.out.println("Список с первым вставленным элементом: " + list);

        // вставка элемента по индексу
        list.add(6, 6);
        System.out.println("Список со вставленным элементом: " + list);

        // удаление узла по значению, пусть выдает true, если элемент был удален
        list.removeByValue(null);
        System.out.println("Список с удаленным элементом: " + list);

        // удаление первого элемента, пусть выдает значение элемента
        System.out.println("Удаленный первый элемент: " + list.removeFirst());

        // разворот списка за линейное время
        list.reverse();
        System.out.println("Развернутый список: " + list);

        // копирование списка
        SinglyLinkedList<Integer> copyList = new SinglyLinkedList<>(list);
        System.out.print("Копированный список: " + copyList);
    }
}