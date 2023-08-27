package ru.academits.petrushin.remove_even_numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveEvenNumbers {
    public static void removeEvenNumbers() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 2, 7, 4, 8));
        System.out.println("Начальный список чисел: " + list);

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                --i;
            }
        }

        System.out.print("Список без четных чисел: " + list);
    }
}