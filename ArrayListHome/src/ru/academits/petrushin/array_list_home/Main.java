package ru.academits.petrushin.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Чтение в список всех строк из файла
        String filePath = "File.txt";

        try {
            System.out.println("Список строк: " + getFileLines(filePath));
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + filePath + " не найден!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Удаление из списка всех четных чисел
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(2, 1, 2, 7, 4, 8));
        System.out.println("Исходный список чисел: " + numbers1);
        removeEvenNumbers(numbers1);
        System.out.println("Список без четных чисел: " + numbers1);
        System.out.println();

        // Создание нового списка без повторяющихся чисел
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 1));
        System.out.println("Исходный список чисел: " + numbers2);
        System.out.println("Список без повторений: " + getNonRepeatingElements(numbers2));
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
        for (int i = numbers.size() - 1; i >= 0; --i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static<E> ArrayList<E> getNonRepeatingElements(ArrayList<E> elements) {
        ArrayList<E> nonRepeatingElements = new ArrayList<>(elements.size());

        for (E element : elements) {
            if (!nonRepeatingElements.contains(element)) {
                nonRepeatingElements.add(element);
            }
        }

        return nonRepeatingElements;
    }
}