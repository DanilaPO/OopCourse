package ru.acaemits.petrushin.matrix_main;

import ru.acaemits.petrushin.matrix.Matrix;
import ru.acaemits.petrushin.matrix_vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {3, 0, 4, 1};
        double[] array2 = {3, 5, 9, 2};
        double[] array3 = {6, 4, 7, 2};
        double[] array4 = {9, 7, 1, 9};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);

        Vector[] vectorsArray = {vector1, vector2, vector3, vector4};
        Vector[] vectorsArray2 = {vector3, vector4};

        Matrix matrix1 = new Matrix(vectorsArray);
        Matrix matrix2 = new Matrix(vectorsArray2);

        // Получение размеров матрицы
        System.out.println("Размер матрицы: " + matrix1.getSize());
        System.out.println();

        // Получение вектора-строки по индексу
        System.out.println("Получение вектора-строки по индексу: " + matrix1.getStringVector(0));
        System.out.println();

        // Задание вектора-строки по индексу
        matrix1.setStringVector(0, vector4);
        System.out.println("Результат задания вектора-строки по индексу: " + matrix1);
        System.out.println();

        // c. Получение вектора-столбца по индексу
        System.out.println("Получение вектора-столбца по индексу: " + matrix1.getColumnVector(0));
        System.out.println();

        //d. Транспонирование матрицы
        matrix1.transpose();
        System.out.println("Транспонирование матрицы: " + matrix1);
        System.out.println();

        // e. Умножение на скаляр
        matrix1.multiplyByScalar(2);
        System.out.println("Умножение на скаляр: " + matrix1);
        System.out.println();

        // f. Вычисление определителя матрицы
        System.out.println("Вычисление определителя матрицы: " + matrix1.getDeterminant());
        System.out.println();

        // h. умножение матрицы на вектор
        matrix1.multiplyByVector(vector2);
        System.out.println("Умножение матрицы на вектор: " + matrix1);
        System.out.println();

        // i. Сложение матриц
        matrix1.add(matrix2);
        System.out.println("Сложение матриц: " + matrix1);
        System.out.println();

        // j. Вычитание матриц
        matrix1.subtract(matrix2);
        System.out.println("Сложение матриц: " + matrix1);
        System.out.println();

        // a. Сложение матриц - статический метод
        System.out.println("Сложение матриц - статический метод: " + Matrix.getSum(matrix1, matrix2));
        System.out.println();

        // b. Вычитание матриц - статический метод
        System.out.println("Вычитание матриц - статический метод: " + Matrix.getDifference(matrix1, matrix2));
        System.out.println();

        // c. Умножение матриц - статический метод
        System.out.println("Умножение матриц - статический метод: " + Matrix.getMultiply(matrix1, matrix2));
    }
}