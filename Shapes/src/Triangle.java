class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getWidth() {
        double[] arrayX = {x1, x2, x3};
        double max = arrayX[0];
        double min = arrayX[0];

        for (int i = 0; i < arrayX.length; ++i) {
            if (arrayX[i] >= max) {
                max = arrayX[i];
            }
            if (arrayX[i] < min) {
                min = arrayX[i];
            }
        }

        return max – min;
    }

    public double getHeight() {
        double[] arrayY = {y1, y2, y3};
        double max = arrayY[0];
        double min = arrayY[0];

        for (int i = 0; i < arrayY.length; ++i) {
            if (arrayY[i] >= max) {
                max = arrayY[i];
            }

        }

        return max – min;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }
}