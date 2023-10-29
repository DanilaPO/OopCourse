package ru.academits.petrushin.shapes_main;

import ru.academits.petrushin.shapes_comporators.PerimeterComparator;
import ru.academits.petrushin.shapes_comporators.AreaComparator;
import ru.academits.petrushin.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapesArray = {
                new Square(5),
                new Rectangle(2, 5),
                new Circle(3),
                new SemiCircle(4),
                new Triangle(4, 2, 1, 7, 8, 6)
        };

        // распечатка информации о фигуре с наибольшей площадью
        Arrays.sort(shapesArray, new AreaComparator());

        System.out.println("Фигура с наибольшей площадью: " + shapesArray[0] + System.lineSeparator());

        // распечатка информации о фигуре со вторым по величине периметром
        Arrays.sort(shapesArray, new PerimeterComparator());

        System.out.println("Фигура вторая по величине периметра: " + shapesArray[1]);
    }
}