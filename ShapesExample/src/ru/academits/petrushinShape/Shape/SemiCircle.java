package ru.academits.petrushinShape.Shape;

public class SemiCircle implements Shape {
    private final double radius;

    public SemiCircle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.pow(radius, 2) * Math.PI / 2;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius + 2 * radius;
    }

    @Override
    public String toString() {
        return "Полукруг с радиусом: " +
                radius +
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

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }

    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        SemiCircle semiCircle = (SemiCircle) shape;

        return radius == semiCircle.radius;
    }
}
