package ru.acaemits.petrushin.matrix;

import ru.academits.petrushin.vector.Vector;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк не может быть 0 или меньше 0. Передано: строки " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов не может быть 0 или меньше 0. Передано: столбцы " + columnsCount);
        }

        vectors = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            vectors[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        int maxArrayLength = 0;
        int row = 0;

        for (double[] e : array) {
            ++row;

            if (e.length > maxArrayLength) {
                maxArrayLength = e.length;
            }
        }

        vectors = new Vector[row];

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < maxArrayLength; ++j) {
                vectors[i] = new Vector(maxArrayLength, array[i]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым");
        }

        int rows = 0;

        for (Vector e : vectors) {
            ++rows;
        }

        this.vectors = new Vector[rows];

        for (int i = 0; i < rows; ++i) {
            this.vectors[i] = new Vector(vectors[i]);
        }
    }

    public Matrix(Matrix matrix) {
        int rows = 0;

        for (Vector e : matrix.getVectors()) {
            ++rows;
        }

        vectors = new Vector[rows];

        for (int i = 0; i < vectors.length; ++i) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Vector[] getVectors() {
        return vectors;
    }

    public void setVectors(Vector[] vector) {
        this.vectors = vector;
    }

    // a.	Получение размеров матрицы
    public int getRowsCount() {
        return vectors.length;
    }

    public int getColumnsCount() {
        return vectors[0].getSize();
    }

    // b.	Получение вектора-строки по индексу
    public Vector getString(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0. Передано " + index);
        }

        if (index >= vectors.length) {
            throw new IllegalArgumentException("Выход индекса за пределы матрицы (больше " + vectors.length + "). Передано " + index);
        }

        if (vectors == null) {
            throw new NullPointerException("В матрице нет векторов - нужно положить вектор");
        }

        return new Vector(vectors[index]);
    }

    // b.	Задание вектора-строки по индексу
    public void setString(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0. Передан индекс " + index);
        }

        if (index >= this.vectors.length) {
            throw new IllegalArgumentException("Выход индекса за пределы матрицы (больше " + vectors.length + "). Передано " + index);
        }

        Vector vectorCopy = new Vector(vector);

        if (vectorCopy.getSize() != this.vectors[0].getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + this.vectors[0].getSize() + ". Передано " + vectorCopy.getSize());
        }

        this.vectors[index] = vectorCopy;
    }

    // c. Получение вектора-столбца по индексу
    public Vector getColumnVector(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0. Передан " + index);
        }

        if (index >= vectors[0].getSize()) {
            throw new IllegalArgumentException("Выход индекса за пределы матрицы (больше " + vectors[0].getSize() + "). Передано " + index);
        }

        double[] vectorComponents = new double[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            vectorComponents[i] = vectors[i].getComponent(index);
        }

        return new Vector(vectorComponents);
    }

    //d. Транспонирование матрицы
    public void transpose() {
        Vector[] vectors = new Vector[this.vectors[0].getSize()];

        for (int i = 0; i < this.vectors[0].getSize(); ++i) {
            vectors[i] = getColumnVector(i);
        }

        this.vectors = vectors;
    }

    // e. Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (Vector e : vectors) {
            e.multiplyByScalar(scalar);
        }
    }

    // f. Вычисление определителя матрицы
    public double getDeterminant() {
        if (vectors.length != vectors[0].getSize() || vectors.length == 0) {
            throw new UnsupportedOperationException("Матрица не квадратная (не n x n) - у нее нет определителя. Число строк " + vectors.length + ". Число столбцов " + vectors[0].getSize());
        }

        if (vectors.length == 1 && vectors[0].getSize() == 1) {
            return vectors[0].getComponent(0);
        }

        double determinant = 0;
        double currentDeterminant = 1;

        for (int i = 0; i < vectors.length - 1; ++i) {
            Vector currentVector1 = new Vector(vectors[i]);

            currentDeterminant *= currentVector1.getComponent(i);

            for (int j = i + 1; j < vectors.length; ++j) {
                currentDeterminant *= Math.pow(-1, i + 1 + j + 2);

                double multiplier = -vectors[j].getComponent(i) / currentVector1.getComponent(i);

                currentVector1.multiplyByScalar(multiplier);

                Vector currentVector2 = new Vector(vectors[j]);

                currentVector2.add(currentVector1);
            }

            determinant = vectors[vectors.length - 1].getComponent(vectors[0].getSize() - 1) * currentDeterminant;
        }

        return determinant;
    }

    // g. toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    public String toString() {
        StringBuilder row = new StringBuilder("{");

        for (Vector vector : vectors) {
            row.append(vector.toString())
                    .append(", ");
        }

        row.setCharAt(row.length() - 2, '}');
        row.deleteCharAt(row.length() - 1);

        return row.toString();
    }

    // h. умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (this.vectors.length != vector.getSize()) {
            throw new IllegalArgumentException("Некорректный размер переданного вектора. Должно быть " + this.vectors.length + ". Передано " + vector.getSize());
        }

        double[] array = new double[this.vectors.length];


        for (int i = 0; i < this.vectors.length; ++i) {
            for (int j = 0; j < this.vectors[0].getSize(); ++j) {
                array[i] += getVectors()[i].getComponent(j) * vector.getComponent(j);
            }
        }

        return new Vector(array);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount()) {
            throw new UnsupportedOperationException("Некоректный размер строк переданной матрицы, он не равен " + getRowsCount() + ". Передано " + matrix.getRowsCount());
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new UnsupportedOperationException("Некоректный размер столбцов переданной матрицы, он не равен " + getColumnsCount() + ". Передано " + matrix.getColumnsCount());
        }

        for (int i = 0; i < vectors.length; ++i) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount()) {
            throw new UnsupportedOperationException("Некоректный размер строк переданной матрицы, он не равен " + getRowsCount() + ". Передано " + matrix.getRowsCount());
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new UnsupportedOperationException("Некоректный размер столбцов переданной матрицы, он не равен " + getColumnsCount() + ". Передано " + matrix.getColumnsCount());
        }

        for (int i = 0; i < vectors.length; ++i) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    // a. Сложение матриц - статический метод
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new UnsupportedOperationException("Некоректные размеры строк матриц, они не равны. Размер строк первой матрицы " + matrix1.getRowsCount() + ", размер строк второй матрицы " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new UnsupportedOperationException("Некоректные размеры столбцов матриц, они не равны. Размер столбцов первой матрицы " + matrix1.getColumnsCount() + ", размер столбцов второй матрицы " + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    // b. Вычитание матриц - статический метод
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new UnsupportedOperationException("Некоректные размеры строк матриц, они не равны. Размер строк первой матрицы " + matrix1.getRowsCount() + ", размер строк второй матрицы " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new UnsupportedOperationException("Некоректные размеры столбцов матриц, они не равны. Размер столбцов первой матрицы " + matrix1.getColumnsCount() + ", размер столбцов второй матрицы " + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // c. Умножение матриц - статический метод
    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors[0].getSize() != matrix2.vectors.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк другой матрицы. Число столбцов первой матрицы " + matrix1.vectors[0].getSize() + ". Число строк второй матрицы " + matrix2.vectors.length);
        }

        Vector[] rows = new Vector[matrix2.vectors[0].getSize()];

        for (int i = 0; i < matrix2.vectors[0].getSize(); ++i) {
            rows[i] = new Vector(matrix1.multiplyByVector(matrix2.getColumnVector(i)));
        }

        Matrix resultMatrix;

        resultMatrix = new Matrix(rows);

        resultMatrix.transpose();

        return resultMatrix;
    }
}