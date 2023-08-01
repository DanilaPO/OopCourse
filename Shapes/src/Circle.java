class Circle implements Shape {
    private final double diameter;

    public Circle(double radius) {
        diameter = 2 * radius;
    }

    @Override
    public double getWidth() {
        return diameter;
    }

    @Override
    public double getHeight() {
        return diameter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Круг с диаметром: ")
                .append(diameter)
                .append("; ")
                .append("Площадь фигуры: ")
                .append(getArea())
                .append("; ")
                .append("Периметер фигуры: ")
                .append(getPerimeter());

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(diameter);

        return hash;
    }

    public boolean equals(Shape shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Circle circle = (Circle) shape;

        return diameter == circle.diameter;
    }

    @Override
    public double getArea() {
        return diameter / 2 * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return diameter * Math.PI;
    }
}
