package ru.academits.petrushin.sort_by_area;

import ru.academits.petrushin.shapes.Shape;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}