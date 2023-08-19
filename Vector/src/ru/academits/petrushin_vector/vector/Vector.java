package ru.academits.petrushin_vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность n должна быть > 0");
        }

        this.components = new double[dimension];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        this.components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        this.components = new double[dimension];

        System.arraycopy(array, 0, components, 0, dimension);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        String string = "{";

        for (int i = 0; i < components.length; ++i) {
            if (i == components.length - 1) {
                string += components[i] + "}";
            } else {
                string += components[i] + ", ";
            }
        }

        return string;
    }

    // Прибавление к вектору другого вектора
    public void addVector(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    // Вычитание из вектора другого вектора
    public void subtractVector(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] -= vector.components[i];
        }
    }

    // Умножение вектора на скаляр
    public void multiplyByScalar(Vector vector) {
        int integrationBound = Math.min(components.length, vector.components.length);
        double vectorsScalarProduct = 0;
        double componentsSquaredSum = 0;
        double vectorSquaredSum = 0;

        for (int i = 0; i < integrationBound; ++i) {
            vectorsScalarProduct += components[i] * vector.components[i];
            componentsSquaredSum += Math.pow(components[i], 2);
            vectorSquaredSum += Math.pow(vector.components[i], 2);
        }

        double scalar = vectorsScalarProduct / (Math.sqrt(componentsSquaredSum) * Math.sqrt(vectorSquaredSum));

        for (int i = 0; i < integrationBound; ++i) {
            components[i] *= scalar;
        }
    }

    // Разворот вектора (умножение всех компонент на -1)
    public void unwrap() {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= -1;
        }
    }

    // Получение длины вектора
    public double getLength() {
        double sum = 0;

        for (int i = 0; i < getSize(); ++i) {
            sum += Math.pow(components[i], 2);
        }

        return Math.sqrt(sum);
    }

    // Получение и установка компоненты вектора по индексу
    public double getComponent(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("index должен быть < " + components.length);
        }

        if (index < 0) {
            throw new IllegalArgumentException("index должен быть > 0");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("index должен быть < " + components.length);
        }

        if (index < 0) {
            throw new IllegalArgumentException("index должен быть > 0");
        }

        components[index] = component;
    }

    // Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Соответственно, переопределить hashCode
    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    // Сложение двух векторов – должен создаваться новый вектор
    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            vector1.components = Arrays.copyOf(vector1.components, vector2.getSize());
        }

        for (int i = 0; i < vector2.getSize(); ++i) {
            vector1.components[i] += vector2.components[i];
        }

        return new Vector(vector1.components);
    }

    // Вычитание векторов – должен создаваться новый вектор
    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            vector1.components = Arrays.copyOf(vector1.components, vector2.getSize());
        }

        for (int i = 0; i < vector2.getSize(); ++i) {
            vector1.components[i] -= vector2.components[i];
        }

        return new Vector(vector1.components);
    }

    // Скалярное произведение векторов
    public static double getDotProduct(Vector vector1, Vector vector2) {
        int integrationBound = Math.min(vector1.components.length, vector2.components.length);

        double scalarProduct = 0;

        for (int i = 0; i < integrationBound; ++i) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
