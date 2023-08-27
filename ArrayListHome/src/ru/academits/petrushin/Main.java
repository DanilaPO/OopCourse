package ru.academits.petrushin;

import ru.academits.petrushin.lines_list.LinesList;
import ru.academits.petrushin.remove_even_numbers.RemoveEvenNumbers;
import ru.academits.petrushin.remove_same_numbers.RemoveSameNumbers;


public class Main {
    public static void main(String[] args) {
        // Чтение в список всех строк из файла
        LinesList.readLineInList();
        System.out.println();

        // Удаление из списка всех целых чисел
        RemoveEvenNumbers.removeEvenNumbers();
        System.out.println();

        // Создание нового списка без повторяющихся чисел
        RemoveSameNumbers.createNonRepeatingList();
    }
}
