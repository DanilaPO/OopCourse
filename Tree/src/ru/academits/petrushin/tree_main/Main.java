package ru.academits.petrushin.tree_main;

import ru.academits.petrushin.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        Integer x8 = 8;
        tree.add(x8);

        Integer x5 = 5;
        tree.add(x5);

        Integer x7 = 7;
        tree.add(x7);

        Integer x2 = 2;
        tree.add(x2);

        Integer x3 = 3;
        tree.add(x3);

        Integer x1 = 1;
        tree.add(x1);

        Integer x12 = 12;
        tree.add(x12);

        Integer x10 = 10;
        tree.add(x10);

        Integer x9 = 9;
        tree.add(x9);

        Integer x11 = 11;
        tree.add(x11);

        Integer x14 = 14;
        tree.add(x14);

        Integer x13 = 13;
        tree.add(x13);

        Integer x15 = 15;
        tree.add(x15);

        // поиск узла
        System.out.print("Результат поиска узла: " + tree.isInsight(15));
        System.out.println();

        // удаление первого вхождения
        System.out.print("Результат удаление первого вхождения: " + tree.removeFirst());
        System.out.println();

        // получение числа элементов
        System.out.print("Результат получение числа элементов: " + tree.getSize());
        System.out.println();

        // обход в ширину
        System.out.print("Результат обхода в ширину: ");
        tree.breadthFirst();
        System.out.println();

        // обход в глубину без рекурсии
        System.out.print("Результат обхода в глубину без рекурсии: ");
        tree.depthFirst();
        System.out.println();

        // рекурсивный обход в глубину
        System.out.print("Результат рекурсивного обхода в глубину: ");
        tree.recursionDepthFirst();
    }
}