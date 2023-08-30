package ru.academits.petrushin_shapes.main;

import java.util.Comparator;
import java.util.Arrays;

import ru.academits.petrushin_shapes.shape.Circle;
import ru.academits.petrushin_shapes.shape.Rectangle;
import ru.academits.petrushin_shapes.shape.Square;
import ru.academits.petrushin_shapes.shape.SemiCircle;
import ru.academits.petrushin_shapes.shape.Triangle;
import ru.academits.petrushin_shapes.shape.Shape;

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

        // распечатка информации о фигуре со вторым по величине периметром
        Arrays.sort(shapesArray, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return (int) Math.abs(shape2.getPerimeter() - shape1.getPerimeter());
            }
        });

        System.out.print("Фигура вторая по величине периметра: " + shapesArray[1]);
    }
}




