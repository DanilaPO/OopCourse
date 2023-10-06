package ru.acaemits.petrushin.matrix;

import ru.acaemits.petrushin.matrix_vector.Vector;

public class Matrix {
    private int string;
    private int column;
    private double[][] array;
    private Vector[] vector;
    private Matrix matrix;

    public Matrix(int string, int column) {
        if (string == 0 || column == 0) {
            throw new IllegalArgumentException("Количество строк и столбцов не может быть 0");
        }

        this.string = string;
        this.column = column;

        this.array = new double[string][column];

        for (int i = 0; i < string; ++i) {
            for (int j = 0; j < column; ++j) {
                array[i][j] = 0;
            }
        }

        this.vector = new Vector[string];

        for (int i = 0; i < string; ++i) {
            vector[i] = new Vector(array[i]);
        }

        this.matrix = this;
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        this.array = array;

        int maxArrayLength = 0;

        for (double[] e : array) {
            ++string;

            if (e.length > maxArrayLength) {
                maxArrayLength = e.length;
            }
        }

        this.column = maxArrayLength;

        this.vector = new Vector[string];

        for (int i = 0; i < string; ++i) {
            for (int j = 0; j < maxArrayLength; ++j) {
                vector[i] = new Vector(maxArrayLength, array[i]);
            }
        }

        this.matrix = this;
    }

    public Matrix(Vector[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым");
        }

        int maxVectorSize = 0;

        for (Vector e : vector) {
            ++string;

            if (e.getSize() > maxVectorSize) {
                maxVectorSize = e.getSize();
            }
        }

        this.column = maxVectorSize;

        this.array = new double[string][maxVectorSize];

        for (int i = 0; i < string; ++i) {
            for (int j = 0; j < maxVectorSize; ++j) {
                try {
                    array[i][j] = vector[i].getComponents()[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    array[i][j] = 0;
                }
            }
        }

        this.vector = new Vector[string];

        for (int i = 0; i < string; ++i) {
            for (int j = 0; j < maxVectorSize; ++j) {
                this.vector[i] = new Vector(maxVectorSize, array[i]);
            }
        }

        this.matrix = this;
    }

    public Matrix(Matrix matrix) {
        this.string = matrix.string;
        this.column = matrix.column;

        this.vector = new Vector[this.string];

        for (int i = 0; i < this.vector.length; ++i) {
            this.vector[i] = new Vector(matrix.vector[i].getComponents());
        }

        this.array = new double[this.string][this.column];

        for (int i = 0; i < this.string; ++i) {
            for (int j = 0; j < this.column; ++j) {
                try {
                    array[i][j] = vector[i].getComponents()[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    array[i][j] = 0;
                }
            }
        }

        this.matrix = this;
    }

    public int getString() {
        return string;
    }

    public void setString(int string) {
        this.string = string;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double[][] getArray() {
        return array;
    }

    public void setArray(double[][] array) {
        this.array = array;
    }

    public Vector[] getVector() {
        return vector;
    }

    public void setVector(Vector[] vector) {
        this.vector = vector;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    // a.	Получение размеров матрицы
    public int getSize() {
        return string * column;
    }

    // b.	Получение вектора-строки по индексу
    public Vector getStringVector(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0");
        }

        if (index >= vector.length) {
            throw new ArrayIndexOutOfBoundsException("Выход индекса за пределы массива векторов");
        }

        if (vector == null) {
            throw new NullPointerException("В матрице нет векторов - нужно положить вектор");
        }

        return vector[index];
    }

    // b.	Задание вектора-строки по индексу
    public void setStringVector(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0");
        }

        if (index >= this.vector.length) {
            throw new ArrayIndexOutOfBoundsException("Выход индекса за пределы массива векторов");
        }

        if (vector.getSize() < column) {
            this.vector[index] = new Vector(column, vector.getComponents());
            return;
        }

        if (vector.getSize() > column) {
            this.vector[index] = vector;

            for (int i = 0; i < string; ++i) {
                this.vector[i] = new Vector(vector.getSize(), this.vector[i].getComponents());
            }

            return;
        }

        this.vector[index] = vector;
    }

    // c. Получение вектора-столбца по индексу
    public Vector getColumnVector(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0");
        }

        if (index >= column) {
            throw new ArrayIndexOutOfBoundsException("Выход индекса за пределы массива векторов");
        }

        if (vector == null) {
            throw new NullPointerException("В матрице нет векторов - нужно положить вектор");
        }

        double[] array = new double[string];

        for (int i = 0; i < string; ++i) {
            array[i] = vector[i].getComponents()[index];
        }

        return new Vector(array);
    }

    //d. Транспонирование матрицы
    public void transpose() {
        Vector[] vector = new Vector[column];

        for (int i = 0; i < column; ++i) {
            vector[i] = getColumnVector(i);
        }

        this.vector = vector;
    }

    // e. Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < string; ++i) {
            vector[i].multiplyByScalar(scalar);
        }
    }

    // f. Вычисление определителя матрицы
    public double getDeterminant() {
        if (string != column || string == 0) {
            throw new IllegalArgumentException("Матрица не квадратная (не n x n) - у нее нет определителя");
        }

        if (string == 1 && column == 1) {
            return array[0][0];
        }

        double determinant = 0;
        double currentDeterminant = 1;

        for (int i = 0; i < string - 1; ++i) {
            Vector currentVector = vector[i];

            currentDeterminant *= currentVector.getComponent(i);

            for (int j = i + 1; j < string; ++j) {
                currentDeterminant *= Math.pow(-1, i + 1 + j + 2);

                double multiply = -1 * vector[j].getComponent(i) / currentVector.getComponent(i);

                currentVector.multiplyByScalar(multiply);

                vector[j].add(currentVector);

            }

            determinant = vector[string - 1].getComponent(column - 1) * currentDeterminant;
        }

        return determinant;
    }

    // g. toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    public String toString() {
        if (string < 0 || column < 0) {
            throw new IllegalArgumentException("Значение не может быть < 0");
        }

        StringBuilder string = new StringBuilder("{");

        for (Vector e : vector) {
            string.append(e.toString())
                    .append(", ");
        }

        string.setCharAt(string.length() - 2, '}');

        return string.toString();
    }

    // h. умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        double[] array = new double[Math.max(string, vector.getSize())];

        if (string < vector.getSize()) {
            for (int i = 0; i < string; ++i) {
                for (int j = 0; j < column; ++j) {
                    array[i] += getVector()[i].getComponent(j) * vector.getComponent(j);
                }
            }
        } else {
            vector = new Vector(string, vector.getComponents());

            for (int i = 0; i < vector.getSize(); ++i) {
                for (int j = 0; j < column; ++j) {
                    array[i] += getVector()[i].getComponent(j) * vector.getComponent(j);
                }
            }
        }

        return new Vector(array);
    }

    // i. Сложение матриц
    public void add(Matrix matrix) {
        for (int i = 0; i < Math.min(string, matrix.getString()); ++i) {
            vector[i].add(matrix.vector[i]);
        }
    }

    // j. Вычитание матриц
    public void subtract(Matrix matrix) {
        for (int i = 0; i < Math.min(string, matrix.getString()); ++i) {
            vector[i].subtract(matrix.vector[i]);
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
    public static Matrix getMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumn() != matrix2.getString()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк другой матрицы");
        }

        Matrix resultMatrix;

        Vector[] newVectors = new Vector[matrix2.getColumn()];

        for (int i = 0; i < matrix2.getColumn(); ++i) {
            newVectors[i] = new Vector(matrix1.multiplyByVector(matrix2.getColumnVector(i)));
        }

        resultMatrix = new Matrix(newVectors);

        resultMatrix.transpose();

        return resultMatrix;
    }
}