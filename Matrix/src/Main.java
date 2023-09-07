import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        Vector[] vectorsArray = {new Vector(array1), new Vector(vector2)};

        Matrix matrix = new Matrix(3, 5);

        matrix.setStringVector(0, vector1);
        matrix.setStringVector(1, vector2);
        matrix.getColumnVector(0);
        //matrix.transpose();
        System.out.println(matrix);
        //System.out.println(matrix.toString());
    }
}