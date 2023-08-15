package ru.academits.petrushin_shape.main;

import java.util.Comparator;
import java.util.Arrays;

import ru.academits.petrushin_shape.shape.Circle;
import ru.academits.petrushin_shape.shape.Rectangle;
import ru.academits.petrushin_shape.shape.Square;
import ru.academits.petrushin_shape.shape.SemiCircle;
import ru.academits.petrushin_shape.shape.Triangle;
import ru.academits.petrushin_shape.shape.Shape;

public class Main {
    public static void main(String[] args) {
        Shape[] array = {
                new Square(5),
                new Rectangle(2, 5),
                new Circle(3),
                new SemiCircle(4),
                new Triangle(4, 2, 1, 7, 8, 6)
        };

        // распечатка информации о фигуре с наибольшей площадью
        Arrays.sort(array, new Comparator<Shape>() {
            public int compare(Shape o1, Shape o2) {
                return (int) (o2.getArea() - o1.getArea());
            }
        });

        System.out.print("Фигура с наибольшей площадью: " + array[0] + System.lineSeparator());

        // распечатка информации о фигуре со вторым по величине периметром
        Arrays.sort(array, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return (int) (o2.getPerimeter() - o1.getPerimeter());
            }
        });

        System.out.print("Фигура вторая по величине периметра: " + array[1]);
    }
}




