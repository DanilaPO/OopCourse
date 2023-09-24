package ru.academits.petrushin.vector_main;

import ru.academits.petrushin.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        // Сумма векторов нестатическим методом
        vector1.add(vector2);
        System.out.println("Сумма векторов нестатическим методом: " + vector1);

        // Разность векторов нестатическим методом
        vector1.subtract(vector2);
        System.out.println("Разность векторов нестатическим методом: " + vector1);

        // Умножение вектора на скаляр нестатическим методом
        vector1.multiplyByScalar(3);
        System.out.println("Скалярное произведение векторов нестатическим методом: " + vector1);

        // Инверсия вектора
        vector1.reverse();
        System.out.println("Инверсия вектора 1: " + vector1);

        vector2.reverse();
        System.out.println("Инверсия вектора 2: " + vector2);

        // Длина вектора
        System.out.println("Длина вектора 1: " + vector1.getLength());
        System.out.println("Длина вектора 2: " + vector2.getLength());

        // Получение и установка компоненты вектора по индексу
        System.out.println(vector1.getComponent(0));
        vector1.setComponent(0, 8);
        System.out.println(vector1);

        // Переопределение метода equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Переопределен хэш-код
        System.out.println(vector1.equals(vector2));

        // Результат получения нового вектора от суммы векторов статическим методом
        System.out.println("Результат получения нового вектора от суммы векторов статическим методом " + Vector.getSum(vector1, vector2));

        // Результат получения нового вектора от разности векторов статическим методом
        System.out.println("Результат получения нового вектора от разности векторов статическим методом" + Vector.getDifference(vector1, vector2));

        // Скалярное произведение векторов статическим методом
        System.out.println("Результат скалярного произведения векторов статическим методом " + Vector.getScalarProduct(vector1, vector2));
    }
}
