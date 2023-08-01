class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Треугольник с координатами: ")
                .append("x1 = ")
                .append(x1)
                .append(", ")
                .append("y1 = ")
                .append(y1)
                .append(", ")
                .append("x2 = ")
                .append(x2)
                .append(", ")
                .append("y2 = ")
                .append(y2)
                .append(", ")
                .append("x3 = ")
                .append(x3)
                .append(", ")
                .append("y3 = ")
                .append(y3)
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

        hash = prime * hash + Double.hashCode(x1) + Double.hashCode(x2) + Double.hashCode(x3) + Double.hashCode(y1) + Double.hashCode(y2) + Double.hashCode(y3);

        return hash;
    }

    public boolean equals(Shape shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) shape;

        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3 && y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }

    @Override
    public double getArea() {
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));

        double semiPerimeter = (triangleSide1Length + triangleSide2Length + triangleSide3Length) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - triangleSide1Length) * (semiPerimeter - triangleSide2Length) * (semiPerimeter - triangleSide3Length));
    }

    @Override
    public double getPerimeter() {
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));

        return triangleSide1Length + triangleSide2Length + triangleSide3Length;
    }
}