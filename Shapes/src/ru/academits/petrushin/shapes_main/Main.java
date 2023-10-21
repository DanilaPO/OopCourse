package ru.academits.petrushin.shapes_main;

import ru.academits.petrushin.sort_by_perimeter.SortByPerimeter;
import ru.academits.petrushin.sort_by_area.SortByArea;
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
        Arrays.sort(shapesArray, new SortByArea());

        System.out.println("Фигура с наибольшей площадью: " + shapesArray[0] + System.lineSeparator());

        // распечатка информации о фигуре со вторым по величине периметром
        Arrays.sort(shapesArray, new SortByPerimeter());

        System.out.println("Фигура вторая по величине периметра: " + shapesArray[1]);
    }
}