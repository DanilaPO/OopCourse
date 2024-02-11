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

        int columns = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > columns) {
                columns = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            if (columns != vectors[i].getSize()) {
                rows[i] = new Vector(columns);
                rows[i].add(vectors[i]);
                continue;
            }

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
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). " + "Передан индекс: " + index);
        }

        return new Vector(rows[index]);
    }

    // b.	Задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). " + "Передан индекс: " + index);
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + getColumnsCount() + ". Передан индекс: " + vector.getSize());
        }

        rows[index] = new Vector(vector);
    }

    // c. Получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Выход индекса за пределы диапазона (0, " + (rows.length - 1) + "). " + "Передан индекс: " + index);
        }

        double[] vectorComponents = new double[rows.length];

        for (int i = 0; i < rows.length; ++i) {
            vectorComponents[i] = rows[i].getComponent(index);
        }

        return new Vector(vectorComponents);
    }

    // d. Транспонирование матрицы
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
            throw new UnsupportedOperationException("Матрица не квадратная (не n x n) - у нее нет определителя. " + "Число строк " + rows.length + ". Число столбцов " + getColumnsCount());
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        Matrix matrixCopy = new Matrix(rows);

        double determinant = 1;
        final double epsilon = 1.0e-10;

        for (int i = 0; i < rows.length; ++i) {
            double multiplier = 0;

            Vector iMatrixCopyRow = matrixCopy.getRow(i);

            for (int j = i + 1; j < rows.length; ++j) {
                Vector jMatrixCopyRow = matrixCopy.getRow(j);

                if (Math.abs(jMatrixCopyRow.getComponent(i)) <= epsilon) {
                    continue;
                }

                Vector currentRow = new Vector(iMatrixCopyRow);

                multiplier = -jMatrixCopyRow.getComponent(i) / currentRow.getComponent(i);

                currentRow.multiplyByScalar(multiplier);


                matrixCopy.setRow(j, Vector.getSum(jMatrixCopyRow, currentRow));
            }

            if (i == matrixCopy.getRowsCount() - 1) {
                return determinant * (iMatrixCopyRow.getComponent(i) - (matrixCopy.getRow(i - 1).getComponent(i) * multiplier));
            }

            determinant *= iMatrixCopyRow.getComponent(i);
        }

        return determinant;
    }

    // g. toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.setCharAt(stringBuilder.length() - 2, '}');
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    // h. умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + getColumnsCount() + ". Передано " + vector.getSize());
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; ++i) {
            array[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(array);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        checkingMatrixSizeEquality(getRowsCount(), getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount());

        for (int i = 0; i < rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        checkingMatrixSizeEquality(getRowsCount(), getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount());

        for (int i = 0; i < rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    // a. Сложение матриц - статический метод
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkingMatrixSizeEquality(matrix1.getRowsCount(), matrix1.getColumnsCount(), matrix2.getRowsCount(), matrix2.getColumnsCount());
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    // b. Вычитание матриц - статический метод
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkingMatrixSizeEquality(matrix1.getRowsCount(), matrix1.getColumnsCount(), matrix2.getRowsCount(), matrix2.getColumnsCount());
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // c. Умножение матриц - статический метод
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк второй матрицы. " + "Число столбцов первой матрицы " + matrix1.getColumnsCount() + ". Число строк второй матрицы " + matrix2.rows.length);
        }

        int resultMatrixRowsCount = matrix1.getRowsCount();
        int resultMatrixColumnsCount = matrix2.getColumnsCount();

        Vector[] rows = new Vector[resultMatrixRowsCount];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(resultMatrixColumnsCount);

            for (int j = 0; j < resultMatrixColumnsCount; ++j) {
                rows[i].setComponent(j, Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }

        return new Matrix(rows);
    }

    private static void checkingMatrixSizeEquality(int matrix1RowsCount, int matrix1ColumnsCount, int matrix2RowsCount, int matrix2ColumnsCount) {
        if (matrix1RowsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("У обоих матриц разное количество строк: количество строк одной " + "матрицы - " + matrix1RowsCount + ", количество строк второй матрицы - " + matrix2RowsCount);
        }

        if (matrix1ColumnsCount != matrix2ColumnsCount) {
            throw new IllegalArgumentException("У обоих матриц разное количество столбцов: количество столбцов одной " + "матрицы - " + matrix1ColumnsCount + ", количество столбцов второй матрицы - " + matrix2ColumnsCount);
        }
    }
}