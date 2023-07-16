import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число диапазона: ");
        double rangeBeginning = scanner.nextDouble();

        System.out.print("Введите конечное число диапазона: ");
        double rangeEnd = scanner.nextDouble();

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();

        // здесь вводится диапазон
        Range range = new Range(rangeBeginning, rangeEnd);

        // это функция длины диапазона
        System.out.println("Длина диапазона составляет: " + range.getLength());

        // сюда вводится точка, для определения ее принадлежности диапазону
        if (range.isInside(number)) {
            System.out.println("Число относится к диапазону чисел");
        } else {
            System.out.println("Число не относится к диапазону чисел");
        }
    }
}