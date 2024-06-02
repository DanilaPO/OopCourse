package ru.academits.petrushin.tree_main;

import ru.academits.petrushin.tree.BinarySearchTree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.comparingInt(x -> x));

        tree.add(8);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        tree.add(3);
        tree.add(1);
        tree.add(12);
        tree.add(10);
        tree.add(9);
        tree.add(11);
        tree.add(14);
        tree.add(13);
        tree.add(15);

        // поиск узла
        System.out.print("Результат поиска узла: " + tree.contains(2));
        System.out.println();

        // удаление первого вхождения
        System.out.print("Результат удаление первого вхождения: " + tree.remove(12));
        System.out.println();

        // получение числа элементов
        System.out.print("Результат получение числа элементов: " + tree.getSize());
        System.out.println();

        // обход в ширину
        System.out.print("Результат обхода дерева в ширину: ");
        tree.visitInWidth(e -> System.out.print(e + ", "));
        System.out.println();

        // обход в глубину без рекурсии
        System.out.print("Результат обхода дерева в глубину без рекурсии: ");
        tree.visitInDepth(e -> System.out.print(e + ", "));
        System.out.println();

        // рекурсивный обход в глубину
        System.out.print("Результат рекурсивного обхода дерева в глубину: ");
        tree.visitInDepthRecursively(e -> System.out.print(e + ", "));
    }
}