package ru.acaemits.petrushin.matrix;

import ru.academits.petrushin.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк не может быть 0 или меньше 0. Передано: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов не может быть 0 или меньше 0. Передано: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передан null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив");
        }

        int maxColumnsCount = 0;

        for (double[] row : array) {
            if (row.length > maxColumnsCount) {
                maxColumnsCount = row.length;
            }
        }

        if (maxColumnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы оказалось равно 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(maxColumnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Передан null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив");
        }

        int maxLength = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxLength) {
                maxLength = vector.getSize();
            }
        }

        rows = new Vector[maxLength];

        for (int i = 0; i < maxLength; ++i) {
            rows[i] = new Vector(vectors[i]);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    // a.	Получение размеров матрицы
    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    // b.	Получение вектора-строки по индексу
    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Выход  за пределы диапазона (0, " + rows.length + "). Передано: " + index);
        }

        return new Vector(rows[index]);
    }

    // b.	Задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Выход  за пределы диапазона (0, " + rows.length + "). Передано: " + index);
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + getColumnsCount() + ". Передано: " + vector.getSize());
        }

        rows[index] = new Vector(vector);
    }

    // c. Получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Выход  за пределы диапазона (0, " + rows.length + "). Передано: " + index);
        }

        double[] vectorComponents = new double[rows.length];

        for (int i = 0; i < rows.length; ++i) {
            vectorComponents[i] = rows[i].getComponent(index);
        }

        return new Vector(vectorComponents);
    }

    //d. Транспонирование матрицы
    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); ++i) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    // e. Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    // f. Вычисление определителя матрицы
    // способ решения https://yandex.ru/video/preview/4101508309468912937
    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Матрица не квадратная (не n x n) - у нее нет определителя. Число строк " + rows.length + ". Число столбцов " + rows[0].getSize());
        }

        if (rows.length == 1) {
            return getColumnsCount();
        }

        double determinant = 1;

        for (int i = 0; i < rows.length; ++i) {
            double multiplier = 0;

            for (int j = 1 + i; j < rows.length; ++j) {
                if (rows[j].getComponent(i) == 0) {
                    continue;
                }

                Vector currentRow = new Vector(rows[i]);

                multiplier = -rows[j].getComponent(i) / currentRow.getComponent(i);

                currentRow.multiplyByScalar(multiplier);

                rows[j].add(currentRow);
            }

            if (i == rows.length - 1) {
                return determinant * (rows[i].getComponent(i) - (rows[i - 1].getComponent(i) * multiplier));
            }

            determinant *= rows[i].getComponent(i);
        }

        return determinant;
    }

    // g. toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row)
                    .append(", ");
        }

        stringBuilder.setCharAt(stringBuilder.length() - 2, '}');
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    // !!!Не понятно, что нужно
    // h. умножение матрицы на вектор
    // это умножение матрицы на вектор столбец
    public Vector multiplyByColumnVector(Vector vector) {
        if (rows.length == 1) {
            throw new IllegalArgumentException("Для использования умножения матрица-одного вектора на вектор-строку " +
                    "должна быть использована другая функция. Сейчас матрица составляет " + rows.length);
        }

        if (rows.length != vector.getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + rows.length
                    + ". Передано " + vector.getSize());
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < getColumnsCount(); ++j) {
                array[i] += rows[i].getComponent(j) * vector.getComponent(j);
            }
        }

        return new Vector(array);
    }

    // это умножение матрицы-строки на вектор
    public Matrix multiplyByStringVector(Vector vector) {
        if (rows.length > 1) {
            throw new IllegalArgumentException("Для использования умножения матрица на вектор-столбец " +
                    "должна быть использована другая функция. Сейчас матрица составляет " + rows.length);
        }

        if (rows.length != vector.getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + rows.length
                    + ". Передано " + vector.getSize());
        }

        double[][] array = new double[vector.getSize()][vector.getSize()];

        for (int i = 0; i < vector.getSize(); ++i) {
            for (int j = 0; j < vector.getSize(); ++j) {
                array[i][j] = rows[0].getComponent(i) * vector.getComponent(j);
            }
        }

        return new Matrix(array);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        checkMatrix(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        checkMatrix(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    // a. Сложение матриц - статический метод
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrix(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    // b. Вычитание матриц - статический метод
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrix(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // c. Умножение матриц - статический метод
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк другой матрицы. " +
                    "Число столбцов первой матрицы " + matrix1.getColumnsCount() + ". Число строк второй матрицы " +
                    matrix2.rows.length);
        }

        Vector[] rows = new Vector[matrix2.getColumnsCount()];

        for (int i = 0; i < matrix2.getColumnsCount(); ++i) {
            rows[i] = new Vector(matrix1.multiplyByColumnVector(matrix2.getColumn(i)));
        }

        Matrix resultMatrix = new Matrix(rows);

        resultMatrix.transpose();

        return resultMatrix;
    }

    private static void checkMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Некорректное количество строк матриц, они не равны. Количество строк " +
                    "первой матрицы " + matrix1.getRowsCount() + ", количество строк второй матрицы " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Некорректное количество столбцов матриц, они не равны. Количество " +
                    "столбцов первой матрицы " + matrix1.getColumnsCount() + ", количество столбцов второй матрицы " +
                    matrix2.getColumnsCount());
        }
    }

    private void checkMatrix(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("Некорректное количество строк переданной матрицы, оно не равно " +
                    getRowsCount() + ". Передано " + matrix.getRowsCount());
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Некорректное количество столбцов переданной матрицы, оно не равно " +
                    getColumnsCount() + ". Передано " + matrix.getColumnsCount());
        }
    }
}