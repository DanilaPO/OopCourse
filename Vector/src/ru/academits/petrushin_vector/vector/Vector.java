package ru.academits.petrushin_vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а не " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0, а не " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0 || size < array.length) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0  и >= длине массива, а не " + size);
        }

        components = Arrays.copyOf(array, size);

        if (array.length < size) {
            for (int i = array.length; i < size; ++i) {
                components[i] = 0;
            }
        }
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");

        for (int i = 0; i < components.length; ++i) {
            if (i == components.length - 1) {
                string.append(components[i]).append("}");
            } else {
                string.append(components[i]).append(", ");
            }
        }

        return string.toString();
    }

    // Прибавление к вектору другого вектора
    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    // Вычитание из вектора другого вектора
    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] -= vector.components[i];
        }
    }

    // Умножение вектора на скаляр
    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= scalar;
        }
    }

    // Разворот вектора (умножение всех компонент на -1)
    public void reversal() {
        multiplyByScalar(-1);
    }

    // Получение длины вектора
    public double getLength() {
        double sum = 0;

        for (int i = 0; i < getSize(); ++i) {
            sum += components[i] * components[i];
        }

        return Math.sqrt(sum);
    }

    // Получение и установка компоненты вектора по индексу
    public double getComponent(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Index должен быть < " + components.length + ", а не " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index должен быть >= 0" + ", а не " + index);
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Index должен быть < " + components.length + ", а не " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index должен быть >= 0" + ", а не " + index);
        }

        components[index] = component;
    }

    // Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Соответственно, переопределить hashCode
    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
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
        vector1.add(vector2);

        return vector1;
    }

    // Вычитание векторов – должен создаваться новый вектор
    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.subtract(vector2);

        return vector1;
    }

    // Скалярное произведение векторов
    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int limit = Math.min(vector1.components.length, vector2.components.length);

        double scalarProduct = 0;

        for (int i = 0; i < limit; ++i) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
