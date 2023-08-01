class SemiCircle implements Shape {
    private final double radius;

    public SemiCircle(double radius) {
        this.radius = radius;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Полукруг с радиусом: ")
                .append(radius)
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

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }

    public boolean equals(Shape shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        SemiCircle semiCircle = (SemiCircle) shape;

        return radius == semiCircle.radius;
    }

    @Override
    public double getArea() {
        return (2 * radius / 2 * Math.PI) / 2;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius + 2 * radius;
    }
}
