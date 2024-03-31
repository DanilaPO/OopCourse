package ru.academits.petrushin.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Чтение в список всех строк из файла
        try {
            String filePath = "File.txt";
            System.out.println("Список строк: " + getFileLines(filePath));
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.print("Файл  не найден!");
        }

        // Удаление из списка всех целых чисел
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(2, 1, 2, 7, 4, 8));
        System.out.println("Исходный список чисел: " + numbers1);
        removeEvenNumbers(numbers1);
        System.out.println("Список без целых чисел: " + numbers1);
        System.out.println();

        // Создание нового списка без повторяющихся чисел
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 1));
        System.out.println("Исходный список чисел: " + numbers2);
        System.out.println("Список без повторений: " + getNonRepeatingNumbers(numbers2));
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> fileLines = new ArrayList<>();
            String line = reader.readLine();

            while (line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }

            return fileLines;
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

    public static ArrayList<Integer> getNonRepeatingNumbers(ArrayList<Integer> numbers) {
        ArrayList<Integer> nonRepeatingNumbers = new ArrayList<>(numbers.size());

        for (Integer integer : numbers) {
            if (!nonRepeatingNumbers.contains(integer)) {
                nonRepeatingNumbers.add(integer);
            }
        }

        return nonRepeatingNumbers;
    }
}