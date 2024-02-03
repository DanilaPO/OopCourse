package ru.academits.petrushin.example2;

import java.util.Scanner;
import java.util.stream.DoubleStream;


public class Main {
    public static void main(String[] args) {
        // Создать бесконечный поток корней чисел. С консоли прочитать число – сколько элементов нужно вычислить,
        //затем – распечатать эти элементы

        System.out.print("Введите сколько элементов корней потока чисел нужно вычислить: ");
        Scanner scanner = new Scanner(System.in);
        int numberIndex = scanner.nextInt();

        DoubleStream roots = DoubleStream.iterate(0, x -> x + 1).map(x -> Math.sqrt(x)).limit(numberIndex);
        roots.forEach(System.out::println);

        // *Попробовать реализовать бесконечный поток чисел Фиббоначчи

        DoubleStream f = DoubleStream.iterate(0, x -> x + 1).map(x -> (Math.pow((1 + Math.sqrt(5)) / 2, x) -
                Math.pow((1 - Math.sqrt(5)) / 2, x)) / Math.sqrt(5)).limit(numberIndex);
        f.forEach(x -> System.out.println((int) x));
    }
}
