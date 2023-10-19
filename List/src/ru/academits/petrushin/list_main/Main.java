package ru.academits.petrushin.list_main;

import ru.academits.petrushin.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        Integer x = 0;
        Integer x1 = 1;
        Integer x2 = 2;
        Integer x3 = 3;
        Integer x4 = 4;

        list.add(x);
        list.add(x1);
        list.add(x2);
        list.add(x3);
        list.add(x4);

        // получение размера списка
        System.out.println("Размер списка: " + list.getSize());

        // получение значение первого элемента
        System.out.println("Первый элемент списка: " + list.getFirstItem());

        // получение значения по указанному индексу
        System.out.println("Получение значения элемента по индексу: " + list.getItem(1));

        // изменение значения по указанному индексу - пусть выдает старое значение
        System.out.println("Прежнее значение замененного элемента: " + list.setItem(2, 2));
        System.out.println(list);

        // удаление элемента по индексу, пусть выдает значение элемента
        System.out.println("Прежнее значение удаленного элемента: " + list.remove(3));
        System.out.println(list);

        // вставка элемента в начало
        list.add(8);
        System.out.println("Список с первым вставленным элементом: " + list);

        // вставка элемента по индексу
        list.add(4, 6);
        System.out.println("Список со вставленным элементом: " + list);

        // удаление узла по значению, пусть выдает true, если элемент был удален
        list.remove(x4);
        System.out.println("Список с удаленным элементом: " + list);

        // удаление первого элемента, пусть выдает значение элемента
        System.out.println("Удаленный первый элемен: " + list.remove());

        // разворот списка за линейное время
        list.reverse();
        System.out.println("Развернутый список: " + list);

        // копирование списка
        SinglyLinkedList<Integer> copyList = new SinglyLinkedList<>(list);
        System.out.print("Копированный список: " + copyList);
    }
}