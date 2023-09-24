package ru.acaemits.petrushin.matrix;

import ru.acaemits.petrushin.matrix_vector.Vector;

public class Matrix implements Cloneable {
    private int string;
    private int column;
    private double[][] array;
    private Vector[] vector;

    public Matrix(int string, int column) {
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
    }

    public Matrix(double[][] array) {
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
    }

    public Matrix(Vector[] vector) {
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
    public void setStringVector(int index, Vector newVector) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0");
        }

        if (index >= vector.length) {
            throw new ArrayIndexOutOfBoundsException("Выход индекса за пределы массива векторов");
        }

        if (newVector.getSize() < column) {
            vector[index] = new Vector(column, newVector.getComponents());
            return;
        }

        if (newVector.getSize() > column) {
            vector[index] = newVector;

            for (int i = 0; i < string; ++i) {
                vector[i] = new Vector(newVector.getSize(), vector[i].getComponents());
            }

            return;
        }

        vector[index] = newVector;
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

        double[] newArray = new double[string];

        for (int i = 0; i < string; ++i) {
            newArray[i] = vector[i].getComponents()[index];
        }

        return new Vector(newArray);
    }

    //d. Транспонирование матрицы
    public void transpose() {
        Vector[] newVector = new Vector[column];

        for (int i = 0; i < column; ++i) {
            newVector[i] = getColumnVector(i);
        }

        vector = newVector;
    }

    // e. Умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < string; ++i) {
            vector[i].multiplyByScalar(scalar);
        }
    }

    // g. toString определить так, чтобы результат получался в таком виде: {{1, 2}, {2, 3}}
    public String toString() {
        if (string < 0 || column < 0) {
            throw new IllegalArgumentException("Значение не может быть < 0");
        }

        StringBuilder string = new StringBuilder("{");

        for (Vector e : vector) {
            string.append("{");

            for (double f : e.getComponents()) {
                string.append(f)
                        .append(", ");
            }

            string.setCharAt(string.length() - 2, '}');
            string.setCharAt(string.length() - 1, ',');
            string.append(" ");
        }

        string.setCharAt(string.length() - 2, '}');

        return string.toString();
    }

    // h. умножение матрицы на вектор
//    public void multiplyByVector(Vector multiVector) {
//        for (int i = 0; i < column; ++i) {
//            for (int j = 0; j < string; ++j) {
//                vector[j].getComponents()[j] *= multiVector.getComponents()[i];
//            }
//        }
//    }
}
