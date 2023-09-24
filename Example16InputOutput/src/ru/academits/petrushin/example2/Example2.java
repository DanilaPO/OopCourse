package ru.academits.petrushin.example2;

import java.io.IOException;
import java.io.PrintWriter;

public class Example2 {
    public static void main(String[] args) throws IOException {
        try (PrintWriter outputText = new PrintWriter("fileOutputExm2.txt")) {
            StringBuilder stringbuilder = new StringBuilder();

            for (int i = 1; i <= 100; ++i) {
                stringbuilder.append("<<Строка ")
                        .append(i)
                        .append(">>, ");
            }

            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length() + 1);

            outputText.print(stringbuilder);

            outputText.printf("%n<<Строка %d>>%n", 101);
            outputText.println("<<Строка 102>>");
            outputText.print("<<Строка 103>>");
        }
    }
}
