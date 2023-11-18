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

        int size = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > size) {
                size = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(size);
            setRow(i, vectors[i]);
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
        if (index < 0 || index > rows.length - 1) {
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). Передан индекс: " + index);
        }

        return new Vector(rows[index]);
    }

    // b.	Задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        if (index < 0 || index > rows.length - 1) {
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). Передан индекс: " + index);
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + getColumnsCount() + ". Передан индекс: " + vector.getSize());
        }

        rows[index] = new Vector(vector);
    }

    // c. Получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        if (index < 0 || index > rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). Передан индекс: " + index);
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
            return rows[0].getComponent(0);
        }

        Matrix matrixCopy = new Matrix(rows);

        double determinant = 1;
        double epsilon = 1.0e-10;

        for (int i = 0; i < rows.length; ++i) {
            double multiplier = 0;

            for (int j = 1 + i; j < rows.length; ++j) {
                if (Math.abs(matrixCopy.getRow(j).getComponent(i)) <= epsilon) {
                    continue;
                }

                Vector currentRow = new Vector(matrixCopy.getRow(i));

                multiplier = -matrixCopy.getRow(j).getComponent(i) / currentRow.getComponent(i);

                currentRow.multiplyByScalar(multiplier);


                matrixCopy.setRow(j, Vector.getSum(matrixCopy.getRow(j), currentRow));
            }

            if (i == matrixCopy.getRowsCount() - 1) {
                return determinant * (matrixCopy.getRow(i).getComponent(i) - (matrixCopy.getRow(i - 1).getComponent(i) * multiplier));
            }

            determinant *= matrixCopy.getRow(i).getComponent(i);
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

    // h. умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + getColumnsCount()
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
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    // b. Вычитание матриц - статический метод
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // c. Умножение матриц - статический метод
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк второй матрицы. " +
                    "Число столбцов первой матрицы " + matrix1.getColumnsCount() + ". Число строк второй матрицы " +
                    matrix2.rows.length);
        }

        Vector[] rows = new Vector[matrix2.getColumnsCount()];

        for (int i = 0; i < matrix2.getColumnsCount(); ++i) {
            rows[i] = new Vector(matrix1.multiplyByVector(matrix2.getColumn(i)));
        }

        Matrix resultMatrix = new Matrix(rows);

        resultMatrix.transpose();

        return resultMatrix;
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