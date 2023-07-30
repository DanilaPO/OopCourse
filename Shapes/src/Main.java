public class Main {
    public static void main(String[] args) {
       Shape square = new Square(5);
       System.out.print(square.getArea());

       Shape rectangle = new Rectangle(2, 5);
       System.out.print(rectangle.getArea());

       Shape circle = new Circle(3);
       System.out.print(circle.getArea());
    }
}