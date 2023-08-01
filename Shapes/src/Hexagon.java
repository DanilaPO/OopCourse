public class Hexagon implements Shape {
    private final double hexagonFace;

    public Hexagon(double hexagonFace) {
        this.hexagonFace = hexagonFace;
    }

    @Override
    public double getWidth() {
        return hexagonFace;
    }

    @Override
    public double getHeight() {
        return hexagonFace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Правилный шестигранник с длиной гарни: ")
                .append(hexagonFace)
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

        hash = prime * hash + Double.hashCode(hexagonFace);

        return hash;
    }

    public boolean equals(Shape shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Hexagon hexagon = (Hexagon) shape;

        return hexagonFace == hexagon.hexagonFace;
    }

    @Override
    public double getArea() {
        return 3 * Math.sqrt(3) * Math.pow(hexagonFace, 2);
    }

    @Override
    public double getPerimeter() {
        return hexagonFace * 6;
    }
}
