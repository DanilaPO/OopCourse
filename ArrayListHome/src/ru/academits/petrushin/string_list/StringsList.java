package ru.academits.petrushin.string_list;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

public class StringsList {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new FileInputStream("File.txt"))) {

            ArrayList<String> list = new ArrayList<>();

            String[] stringsArray;

            while (scanner.hasNextLine()) {
                stringsArray = scanner.nextLine().split(" ");

                list.addAll(Arrays.asList(stringsArray));
            }

            System.out.print(list);
        }
    }
}
