public class Main {
    public static void main(String[] args) {
        Shape[] shapesArray = {new Square(5), new Rectangle(2, 5), new Circle(3), new SemiCircle(4), new Hexagon(4), new Triangle(4, 2, 1, 7, 8, 6)};

        // распечатка информации о фигуре с наибольшей площадью
        System.out.print(maxAreaShape(shapesArray));
        System.out.println();

        // распечатка информации о фигуре со вторым по величине периметром
        sortShapesArray (shapesArray);
        System.out.print("Фигура вторая по величине периметра: " + shapesArray[1]);
    }

    public static Shape maxAreaShape(Shape[] shapesArray) {
        double maxArea = 0;
        Shape maxAreaShape = null;

        for (Shape e : shapesArray) {
            if (e.getArea() > maxArea) {
                maxArea = e.getArea();
                maxAreaShape = e;
            }
        }

        return maxAreaShape;
    }

    public static void sortShapesArray (Shape[] shapesArray) {
        for (int i = 0; i < shapesArray.length; ++i) {
            boolean needSort = false;

            for (int j = 1; j < shapesArray.length - i; ++j) {
                if (shapesArray[j - 1].getPerimeter() < shapesArray[j].getPerimeter()) {
                    Shape tmp = shapesArray[j - 1];
                    shapesArray[j - 1] = shapesArray[j];
                    shapesArray[j] = tmp;

                    needSort = true;
                }
            }

            if (needSort) {
                return;
            }
        }
    }
}



