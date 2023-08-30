import java.util.Arrays;

class Vector {
    private int n;
    private double component;
    private double[] vectorComponents;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть > 0");
        }

        this.n = n;
        this.component = 0;
        this.vectorComponents = new double[n];

        for (int i = 0; i < n; ++i) {
            this.vectorComponents[i] = 0;
        }
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.component = vector.component;
        this.vectorComponents = vector.vectorComponents;
    }

    public Vector(double[] array) {
        this.n = array.length;
        this.vectorComponents = new double[n];

        for (int i = 0; i < n; ++i) {
            vectorComponents[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        this.vectorComponents = new double[n];

        if (array.length < n) {
            for (int j = array.length; j < n; ++j) {
                vectorComponents[j] = 0;

                for (int i = 0; i < array.length; ++i) {
                    vectorComponents[i] = array[i];
                }
            }
        } else {
            for (int i = 0; i < n; ++i) {
                vectorComponents[i] = array[i];
            }
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getComponent() {
        return component;
    }

    public void setComponent(double component) {
        this.component = component;
    }

    public double[] getVectorComponents() {
        return vectorComponents;
    }

    public void setVectorComponents(double[] vectorComponents) {
        this.vectorComponents = vectorComponents;
    }

    public int getSize() {
        return vectorComponents.length;
    }

    public String toString() {
        return Arrays.toString(vectorComponents);
    }

    // Прибавление к вектору другого вектора
    public double[] getVectorsSum(Vector vector) {
        if (getSize() > vector.getSize()) {
            vector.n = getSize();
            vector.vectorComponents = new double[n];

            for (int i = 0; i < getSize(); ++i) {
                vectorComponents[i] = vectorComponents[i] + vector.vectorComponents[i];
            }

            return vectorComponents;
        }

        n = vector.getSize();
        vectorComponents = new double[n];

        for (int i = 0; i < vector.getSize(); ++i) {
            vectorComponents[i] = vectorComponents[i] + vector.vectorComponents[i];
        }

        return vectorComponents;
    }

    // Вычитание из вектора другого вектора
    public double[] getVectorsSubtraction(Vector vector) {
        if (getSize() > vector.getSize()) {
            vector.n = getSize();
            vector.vectorComponents = new double[n];

            for (int i = 0; i < getSize(); ++i) {
                vectorComponents[i] = vectorComponents[i] - vector.vectorComponents[i];
            }

            return vectorComponents;
        }

        n = vector.getSize();
        vectorComponents = new double[n];

        for (int i = 0; i < vector.getSize(); ++i) {
            vectorComponents[i] = vectorComponents[i] - vector.vectorComponents[i];
        }

        return vectorComponents;
    }

    // Умножение вектора на скаляр
    public double getVectorsDotProduct(Vector vector) {
        double volumeDotProduct = 0;

        if (getSize() > vector.getSize()) {
            for (int i = 0; i < vector.getSize(); ++i) {
                volumeDotProduct += vectorComponents[i] * vector.vectorComponents[i];
            }

            return volumeDotProduct;
        }

        for (int i = 0; i < getSize(); ++i) {
            volumeDotProduct += vectorComponents[i] * vector.vectorComponents[i];
        }

        return volumeDotProduct;
    }

    // Разворот вектора (умножение всех компонент на -1)
    public void getVectorReversal() {
        for (int i = 0; i < getSize(); ++i) {
            vectorComponents[i] *= -1;
        }
    }

    // Получение длины вектора
    public double getVectorLength() {
        double componentsSum = 0;

        for (int i = 0; i < getSize(); ++i) {
            componentsSum += Math.pow(vectorComponents[i], 2);
        }

        return Math.sqrt(componentsSum);
    }

    // Получение и установка компоненты вектора по индексу
    public void getAndInstallComponent(int index, double newComponent) {
        if (index > getSize()) {
            throw new IllegalArgumentException("index должен быть <= " + getSize());
        }

        vectorComponents[index] = newComponent;
    }

    // Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Соответственно, переопределить hashCode
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + n;
        hash = prime * hash + Double.hashCode(component);
        hash = prime * hash + Arrays.hashCode(vectorComponents);
        return hash;
    }

    public boolean equals(Vector vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != getClass()) {
            return false;
        }

        if (getSize() != vector.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); ++i) {
            if (vectorComponents[i] != vector.vectorComponents[i]) {
                return false;
            }
        }

        return true;
    }

    // Сложение двух векторов – должен создаваться новый вектор
    public static Vector newSumVector(Vector vector1, Vector vector2) {
        if (vector1.getSize() > vector2.getSize()) {
            double[] newVectorComponents = new double[vector1.getSize()];

            for (int i = 0; i < vector1.getSize(); ++i) {
                if (i < vector2.getSize()) {
                    newVectorComponents[i] = vector1.vectorComponents[i] + vector2.vectorComponents[i];
                } else {
                    newVectorComponents[i] = vector1.vectorComponents[i];
                }
            }

            return new Vector(newVectorComponents);
        }

        double[] newVectorComponents = new double[vector2.getSize()];

        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i < vector1.getSize()) {
                newVectorComponents[i] = vector1.vectorComponents[i] + vector2.vectorComponents[i];
            } else {
                newVectorComponents[i] = vector2.vectorComponents[i];
            }
        }

        return new Vector(newVectorComponents);
    }

    // Вычитание векторов – должен создаваться новый вектор
    public static Vector newSubtractionVector(Vector vector1, Vector vector2) {
        if (vector1.getSize() > vector2.getSize()) {
            double[] newVectorComponents = new double[vector1.getSize()];

            for (int i = 0; i < vector1.getSize(); ++i) {
                if (i < vector2.getSize()) {
                    newVectorComponents[i] = vector1.vectorComponents[i] - vector2.vectorComponents[i];
                } else {
                    newVectorComponents[i] = vector1.vectorComponents[i];
                }
            }

            return new Vector(newVectorComponents);
        }

        double[] newVectorComponents = new double[vector2.getSize()];

        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i < vector1.getSize()) {
                newVectorComponents[i] = vector1.vectorComponents[i] - vector2.vectorComponents[i];
            } else {
                newVectorComponents[i] = vector2.vectorComponents[i];
            }
        }

        return new Vector(newVectorComponents);
    }

    // Скалярное произведение векторов
    public static double getVectorsDotProduct(Vector vector1, Vector vector2) {
        double volumeDotProduct = 0;

        if (vector1.getSize() > vector2.getSize()) {
            for (int i = 0; i < vector2.getSize(); ++i) {
                volumeDotProduct += vector1.vectorComponents[i] * vector2.vectorComponents[i];
            }

            return volumeDotProduct;
        }

        for (int i = 0; i < vector1.getSize(); ++i) {
            volumeDotProduct += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return volumeDotProduct;
    }
}
