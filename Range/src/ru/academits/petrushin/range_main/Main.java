package ru.academits.petrushin.range_main;

import ru.academits.petrushin.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число первого интервала чисел: ");
        double from1 = scanner.nextDouble();

        System.out.print("Введите конечное число первого интервала чисел: ");
        double to1 = scanner.nextDouble();

        System.out.print("Введите первое число второго интервала чисел: ");
        double from2 = scanner.nextDouble();

        System.out.print("Введите конечное число второго интервала чисел: ");
        double to2 = scanner.nextDouble();

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();
        System.out.println();

        // здесь два диапазона
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        // это функция длины диапазона
        System.out.println("Длина диапазона 1 составляет: " + range1.getLength());
        System.out.println("Длина диапазона 2 составляет: " + range2.getLength());
        System.out.println();

        // сюда вводится точка, для определения ее принадлежности диапазону
        if (range1.isInside(number)) {
            if (range2.isInside(number)) {
                System.out.println("Число относится к диапазону чисел # 1 и # 2");
                System.out.println();
            } else {
                System.out.println("Число относится к диапазону чисел # 1");
                System.out.println();
            }
        } else {
            if (range2.isInside(number)) {
                System.out.println("Число относится к диапазону чисел # 2");
                System.out.println();
            } else {
                System.out.println("Число не относится ни к какому диапазону чисел");
                System.out.println();
            }
        }

        // функция вызова результата пересечения диапазонов
        System.out.println("Пересечение диапазонов: " + range1.getIntersection(range2));
        System.out.println();

        // получение объединения двух интервалов
        System.out.print("Результат объединения двух интервалов: " + Arrays.toString(range1.getUnion(range2)));
        System.out.println();
        System.out.println();

        // Получение разности двух интервалов
        System.out.print("Результат разности двух интервалов: " + Arrays.toString(range1.getDifference(range2)));
    }
}