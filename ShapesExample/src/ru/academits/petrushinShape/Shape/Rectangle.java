package ru.academits.petrushinShape.Shape;

public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Прямоугольник с длиной сторон: " +
                "сторона 1 = " +
                width +
                ", " +
                "сторона 2 = " +
                height +
                "; " +
                "Площадь фигуры: " +
                getArea() +
                "; " +
                "Периметр фигуры: " +
                getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);

        return hash;
    }

    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) shape;

        return width == rectangle.width && height == rectangle.height;
    }
}
