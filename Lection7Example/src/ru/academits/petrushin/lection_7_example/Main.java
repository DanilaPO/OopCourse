package ru.academits.petrushin.lection_7_example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Чтение в список всех строк из файла
        String filePath = "File.txt";
        try {
            readLinesList(filePath);
            System.out.println("Список строк: " + readLinesList(filePath));
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException("Файл не найден!");
        } finally {
            // Удаление из списка всех целых чисел
            ArrayList<Integer> integerNumbersList = new ArrayList<>(Arrays.asList(2, 1, 2, 7, 4, 8));
            System.out.println("Исходный список чисел, в том числе целых чисел: " + integerNumbersList);
            removeEvenNumbers(integerNumbersList);
            System.out.println("Списко без целых чисел: " + integerNumbersList);
            System.out.println();

            // Создание нового списка без повторяющихся чисел
            ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 1));
            System.out.println("Исходный список чисел, в том числе повторяющихся чисел: " + numbers);
            System.out.println("Список без повторений: " + getNonRepeatingNumbersList(numbers));
        }
    }

    public static ArrayList<String> readLinesList(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> fileStrings = new ArrayList<>();
            String line = reader.readLine();

            while (line != null) {
                fileStrings.add(line);
                line = reader.readLine();
            }

            return fileStrings;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> getNonRepeatingNumbersList(ArrayList<Integer> numbers) {
        ArrayList<Integer> nonRepeatingNumbersList = new ArrayList<>(numbers.size());

        for (Integer integer : numbers) {
            if (!nonRepeatingNumbersList.contains(integer)) {
                nonRepeatingNumbersList.add(integer);
            }
        }

        return nonRepeatingNumbersList;
    }
}