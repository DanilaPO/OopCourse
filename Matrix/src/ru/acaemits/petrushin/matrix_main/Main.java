package ru.acaemits.petrushin.matrix_main;

import ru.acaemits.petrushin.matrix.Matrix;
import ru.acaemits.petrushin.matrix_vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 4};
        double[] array2 = {1, 2, 8};
        double[] array3 = {-2, -2, -2, -2};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectorsArray = {new Vector(array1), new Vector(vector2)};

        double[][] array = {array1, array2, array3};

        Matrix matrix = new Matrix(vectorsArray);

        Matrix matrix2 = new Matrix(matrix);

        matrix.multiplyByScalar(-2);

        System.out.println(matrix);
    }
}