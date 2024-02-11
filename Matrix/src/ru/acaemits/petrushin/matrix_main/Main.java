package ru.acaemits.petrushin.matrix_main;

import ru.acaemits.petrushin.matrix.Matrix;
import ru.academits.petrushin.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {2, 4};
        double[] array2 = {-2, 1};
        double[] array3 = {-1, 0, 2};
        double[] array4 = {1, 2, -1};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);

        Vector[] vectorsArray1 = {vector1, vector2};
        Vector[] vectorsArray2 = {vector3, vector4};

        Matrix matrix1 = new Matrix(vectorsArray1);
        Matrix matrix2 = new Matrix(vectorsArray2);

        // Получение размеров матрицы
        System.out.println("Размер матрицы: строки " + matrix1.getRowsCount() + ", столбцы " + matrix1.getColumnsCount());
        System.out.println();

        // Получение вектора-строки по индексу
        System.out.println("Получение вектора-строки по индексу: " + matrix1.getRow(1));
        System.out.println();

        // Задание вектора-строки по индексу
        matrix1.setRow(0, vector1);
        System.out.println("Результат задания вектора-строки по индексу: " + matrix1);
        System.out.println();

        // c. Получение вектора-столбца по индексу
        System.out.println("Получение вектора-столбца по индексу: " + matrix1.getColumn(1));
        System.out.println();

        // d. Транспонирование матрицы
        matrix1.transpose();
        System.out.println("Транспонирование матрицы: " + matrix1);
        System.out.println();

        // e. Умножение на скаляр
        matrix1.multiplyByScalar(2);
        System.out.println("Умножение на скаляр: " + matrix1);
        System.out.println();

        // f. Вычисление определителя матрицы
        System.out.println("Вычисление определителя матрицы: " + matrix1.getDeterminant());
        System.out.println(matrix1);

        // h. умножение матрицы на вектор
        System.out.println("Умножение матрицы на вектор: " + matrix1.multiplyByVector(vector1));
        System.out.println();

        // i. Сложение матриц
        matrix1.add(matrix1);
        System.out.println("Сложение матриц: " + matrix1);
        System.out.println();

        // j. Вычитание матриц
        matrix1.subtract(matrix1);
        System.out.println("Сложение матриц: " + matrix1);
        System.out.println();

        // a. Сложение матриц - статический метод
        System.out.println("Сложение матриц - статический метод: " + Matrix.getSum(matrix2, matrix2));
        System.out.println();

        // b. Вычитание матриц - статический метод
        System.out.println("Вычитание матриц - статический метод: " + Matrix.getDifference(matrix1, matrix1));
        System.out.println();

        // c. Умножение матриц - статический метод
        System.out.println("Умножение матриц - статический метод: " + Matrix.getProduct(matrix1, matrix2));
    }
}