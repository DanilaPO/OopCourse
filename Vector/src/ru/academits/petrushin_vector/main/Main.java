package ru.academits.petrushin_vector.main;

import ru.academits.petrushin_vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        // Сумма векторов нестатическим методом
        vector1.addVector(vector2);
        System.out.println("Сумма векторов нестатическим методом: " + vector1.toString());

        // Разность векторов нестатическим методом
        vector1.subtractVector(vector2);
        System.out.println("Разность векторов нестатическим методом: " + vector1.toString());

        // Умножение вектора на скаляр нестатическим методом
        vector1.multiplyByScalar(vector2);
        System.out.println("Скалярное произведение векторов нестатическим методом: " + vector1.toString());

        // Инверсия вектора
        vector1.unwrap();
        System.out.println("Инверсия вектора 1: " + vector1.toString());

        vector2.unwrap();
        System.out.println("Инверсия вектора 2: " + vector2.toString());

        // Длина вектора
        System.out.println("Длина вектора 1: " + vector1.getLength());
        System.out.println("Длина вектора 2: " + vector2.getLength());

        // Получение и установка компоненты вектора по индексу
        System.out.println(vector1.getComponent(0));
        vector1.setComponent(0, 8);
        System.out.println(vector1.toString());

        // Переопределение метода equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Переопределен хэш-код
        System.out.println(vector1.equals(vector2));

        // Результат получения нового вектора от суммы векторов статическим методом
        System.out.println("Результат получения нового вектора от суммы векторов статическим методом " + Vector.getSum(vector1, vector2));

        // Результат получения нового вектора от разности векторов статическим методом
        System.out.println("Результат получения нового вектора от разности векторов статическим методом" + Vector.getSubtraction(vector1, vector2));

        // Скалярное произведение векторов татическим методом
        System.out.println("Результат скалярного произведения векторов статическим методом " + Vector.getDotProduct(vector1, vector2));
    }
}
