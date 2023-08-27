package ru.academits.petrushin.lines_list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class LinesList {
    public static void readLineInList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            ArrayList<String> lineList = new ArrayList<>();

            while (reader.ready()) {
                String[] linesArray = new String[]{reader.readLine()};

                lineList.addAll(Arrays.asList(linesArray));
            }

            System.out.print(lineList);
        } catch (IOException e) {
            System.out.print("Файл \"File.txt\" отсутствует, нужно его создать с назваинем \"File.txt\"");
        }
    }
}