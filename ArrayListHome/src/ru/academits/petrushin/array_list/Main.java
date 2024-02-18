package ru.academits.petrushin.array_list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Чтение в список всех строк из файла
        BufferedReader reader = new BufferedReader(new FileReader("File.txt"));
        readLinesList(reader);

        // Удаление из списка всех целых чисел
        ArrayList<Integer> integerNumbersList = new ArrayList<>(Arrays.asList(2, 1, 2, 7, 4, 8));
        removeEvenNumbers(integerNumbersList);

        // Создание нового списка без повторяющихся чисел
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 1));
        System.out.print("Список без повторений: " + getNonRepeatingNumbersList(list));
    }

    public static void readLinesList(BufferedReader reader) throws IOException {
        ArrayList<String> lineList = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {
            lineList.add(reader.readLine());
            line = reader.readLine();
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }
    }

    public static ArrayList<Integer> getNonRepeatingNumbersList(ArrayList<Integer> list) {
        ArrayList<Integer> nonRepeatingNumbersList = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {
            if (nonRepeatingNumbersList.contains(list.get(i))) {
                continue;
            }

            nonRepeatingNumbersList.add(list.get(i));
        }

        return nonRepeatingNumbersList;
    }
}