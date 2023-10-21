package ru.academits.petrushin.sort_by_perimeter;

import ru.academits.petrushin.shapes.Shape;

import java.util.Comparator;

public class SortByPerimeter implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}