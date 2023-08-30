import java.util.Arrays;

class Matrix {
    private int string;
    private int column;
    private Matrix matrix;
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

    public Matrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix(double[][] array) {
        this.array = array;
    }
    public double[][] getArray() {
        return array;
    }

    public void setArray(double[][] array) {
        this.array = array;
    }

    public Matrix(Vector[] vector) {
        this.vector = vector;
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
    public Vector getVector(int index) {
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
    public void setVector(int index, Vector newVector) {
        if (index < 0) {
            throw new IllegalArgumentException("Значение индекса не может быть < 0");
        }

        if (index >= vector.length) {
            throw new ArrayIndexOutOfBoundsException("Выход индекса за пределы массива векторов");
        }

        if (newVector.getSize() < column) {


            vector[index] = new newVector.getVectorComponents();
        }

        vector[index] = newVector;
    }

    public String toString() {
        if (string < 0 || column < 0) {
            throw new IllegalArgumentException("Значение не может быть < 0");
        }

        String string = "";

        for (Vector e : vector) {
            string += Arrays.toString(e.getVectorComponents()) + System.lineSeparator();
        }

        return string;
    }
}
