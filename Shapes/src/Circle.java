class Circle implements Shape {
    private double diameter;

    public Circle (double radius) {
        diameter = 2 * radius;
    }

    public double getWidth() {
        return diameter;
    }

    public double getHeight() {
        return diameter;
    }

    public double getArea() {
        return diameter / 2 * Math.PI;
    }

    public double getPerimeter() {
        return 0;
    }
}
