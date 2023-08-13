package ru.academits.petrushinShape.Shape;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат с длиной стороны: " +
                sideLength +
                "; " +
                "Площадь фигуры: " +
                getArea() +
                "; " +
                "Периметр фигуры: " +
                getPerimeter();
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }

    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) shape;

        return sideLength == square.sideLength;
    }
}