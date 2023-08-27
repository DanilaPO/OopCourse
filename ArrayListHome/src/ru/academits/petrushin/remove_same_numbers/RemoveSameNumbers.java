package ru.academits.petrushin.remove_same_numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveSameNumbers {
    public static void createNonRepeatingList() {
        try {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));

            System.out.println("Начальный список чисел: " + list);

            ArrayList<Integer> nonRepeatingList = new ArrayList<>(list);

            for (int i = 0; i < nonRepeatingList.size(); ++i) {

                for (int j = 0; j < i; ++j) {
                    if (nonRepeatingList.get(i).equals(nonRepeatingList.get(j))) {
                        nonRepeatingList.remove(i);
                        --i;
                    }
                }
            }

            System.out.print("Список без повторений: " + nonRepeatingList);
        } catch (NullPointerException e) {
            System.out.print("Список пуст - его нужно заполнить");
        }
    }
}