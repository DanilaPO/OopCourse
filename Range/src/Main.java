import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число первого интервала чисел: ");
        double firstIntervalBeginning = scanner.nextDouble();

        System.out.print("Введите конечное число первого интервала чисел: ");
        double firstIntervalEnd = scanner.nextDouble();

        System.out.print("Введите первое число второго интервала чисел: ");
        double secondIntervalBeginning = scanner.nextDouble();

        System.out.print("Введите конечное число второго интервала чисел: ");
        double secondIntervalEnd = scanner.nextDouble();

        // здесь вводится диапазон
        Range range = new Range(firstIntervalBeginning, firstIntervalEnd);

        // сюда вводятся точки второго диапозона для определения перечесения
        System.out.println("Результат Пересечения интервалов: " + range.getIntersection(secondIntervalBeginning, secondIntervalEnd));

        // получение объединения двух интервалов
        System.out.print("Результат объединения интревалов: ");

        for (double[] piecesArray : range.getSetsCombining(secondIntervalBeginning, secondIntervalEnd)) {
            System.out.print(Arrays.toString(piecesArray));
        }

        System.out.println();

        // Получение разности двух интервалов
        System.out.print("Результат разности интревалов: ");

        for (double[] piecesArray : range.getSetsDifference(secondIntervalBeginning, secondIntervalEnd)) {
            System.out.print(Arrays.toString(piecesArray));
        }
    }
}