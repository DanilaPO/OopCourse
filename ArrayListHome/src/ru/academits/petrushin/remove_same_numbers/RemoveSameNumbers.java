package ru.academits.petrushin.remove_same_numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveSameNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));

        for (int j = 0; j < list.size(); ++j) {

            for (int i = 0; i < j; ++i) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                    --j;
                }
            }
        }

        System.out.print(list);
    }
}