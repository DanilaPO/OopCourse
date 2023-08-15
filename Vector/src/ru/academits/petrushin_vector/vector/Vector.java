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
        this.components = vector.components;
    }

    //TODO: 16. Vector(double[] array), Vector(int n, double[] array) - массивы не нужно копировать вручную, нужно использовать стандартные функции Arrays.copyOf, System.arraycopy. Сейчас выдаются warning'и
    public Vector(double[] array) {
        this.components = new double[array.length];

        for (int i = 0; i < array.length; ++i) {
            components[i] = array[i];
        }
    }

    //TODO: 16. Vector(double[] array), Vector(int n, double[] array) - массивы не нужно копировать вручную, нужно использовать стандартные функции Arrays.copyOf, System.arraycopy. Сейчас выдаются warning'и
    public Vector(int dimension, double[] array) {
        this.components = new double[dimension];

        for (int i = 0; i < array.length; ++i) {
            components[i] = array[i];
        }
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }

    // Прибавление к вектору другого вектора
    public double[] getVectorsSum(Vector vector) {
        if (components.length > vector.components.length) {
            vector.components = new double[components.length];

            for (int i = 0; i < getSize(); ++i) {
                components[i] = components[i] + vector.components[i];
            }

            return components;
        }

        components = new double[vector.components.length];

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] = components[i] + vector.components[i];
        }

        return components;
    }

    // Вычитание из вектора другого вектора
    public double[] getVectorsSubtraction(Vector vector) {
        if (components.length > vector.components.length) {
            vector.components = new double[components.length];

            for (int i = 0; i < getSize(); ++i) {
                components[i] = components[i] - vector.components[i];
            }

            return components;
        }

        components = new double[vector.components.length];

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] = components[i] - vector.components[i];
        }

        return components;
    }

    // Умножение вектора на скаляр
    public double getVectorsDotProduct(Vector vector) {
        double volumeDotProduct = 0;

        if (components.length > vector.components.length) {
            for (int i = 0; i < vector.components.length; ++i) {
                volumeDotProduct += components[i] * vector.components[i];
            }

            return volumeDotProduct;
        }

        for (int i = 0; i < components.length; ++i) {
            volumeDotProduct += components[i] * vector.components[i];
        }

        return volumeDotProduct;
    }

    // Разворот вектора (умножение всех компонент на -1)
    public void getVectorReversal() {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= -1;
        }
    }

    // Получение длины вектора
    public double getVectorLength() {
        double componentsSum = 0;

        for (int i = 0; i < getSize(); ++i) {
            componentsSum += Math.pow(components[i], 2);
        }

        return Math.sqrt(componentsSum);
    }

    // Получение и установка компоненты вектора по индексу
    public void getAndInstallComponent(int index, double newComponent) {
        if (index > components.length) {
            throw new IllegalArgumentException("index должен быть <= " + components.length);
        }

        components[index] = newComponent;
    }

    // Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Соответственно, переопределить hashCode
    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public boolean equals(Vector vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != getClass()) {
            return false;
        }

        if (components.length != vector.components.length) {
            return false;
        }

        for (int i = 0; i < getSize(); ++i) {
            if (components[i] != vector.components[i]) {
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
                    newVectorComponents[i] = vector1.components[i] + vector2.components[i];
                } else {
                    newVectorComponents[i] = vector1.components[i];
                }
            }

            return new Vector(newVectorComponents);
        }

        double[] newVectorComponents = new double[vector2.getSize()];

        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i < vector1.getSize()) {
                newVectorComponents[i] = vector1.components[i] + vector2.components[i];
            } else {
                newVectorComponents[i] = vector2.components[i];
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
                    newVectorComponents[i] = vector1.components[i] - vector2.components[i];
                } else {
                    newVectorComponents[i] = vector1.components[i];
                }
            }

            return new Vector(newVectorComponents);
        }

        double[] newVectorComponents = new double[vector2.getSize()];

        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i < vector1.getSize()) {
                newVectorComponents[i] = vector1.components[i] - vector2.components[i];
            } else {
                newVectorComponents[i] = vector2.components[i];
            }
        }

        return new Vector(newVectorComponents);
    }

    // Скалярное произведение векторов
    public static double getVectorsDotProduct(Vector vector1, Vector vector2) {
        double volumeDotProduct = 0;

        if (vector1.getSize() > vector2.getSize()) {
            for (int i = 0; i < vector2.getSize(); ++i) {
                volumeDotProduct += vector1.components[i] * vector2.components[i];
            }

            return volumeDotProduct;
        }

        for (int i = 0; i < vector1.getSize(); ++i) {
            volumeDotProduct += vector1.components[i] * vector2.components[i];
        }

        return volumeDotProduct;
    }
}
