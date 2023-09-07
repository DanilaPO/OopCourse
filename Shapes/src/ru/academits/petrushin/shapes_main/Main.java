package ru.academits.petrushin.shapes_main;

import java.util.Comparator;
import java.util.Arrays;

import ru.academits.petrushin.shapes.Circle;
import ru.academits.petrushin.shapes.Rectangle;
import ru.academits.petrushin.shapes.Square;
import ru.academits.petrushin.shapes.SemiCircle;
import ru.academits.petrushin.shapes.Triangle;
import ru.academits.petrushin.shapes.Shape;

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
        Arrays.sort(shapesArray, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return (int) Math.abs(shape2.getArea() - shape1.getArea());
            }
        });

        System.out.print("Фигура с наибольшей площадью: " + shapesArray[0] + System.lineSeparator());

        //TODO:  Не исправлены п.1, 3, 10, 12
        // распечатка информации о фигуре со вторым по величине периметром
        Arrays.sort(shapesArray, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return (int) (shape2.getPerimeter() - shape1.getPerimeter());
            }
        });

        System.out.print("Фигура вторая по величине периметра: " + shapesArray[1]);
    }
}




