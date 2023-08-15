package ru.academits.petrushin_vector.main;

import ru.academits.petrushin_vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 8};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        //TODO: непечается
        // Сумма векторов нестатическим методом
        System.out.println("Сумма векторов нестатическим методом: " + vector1.getVectorsSum(vector2));

        // Разность векторов нестатическим методом
        System.out.println("Разность векторов нестатическим методом: " + vector1.getVectorsSubtraction(vector2));

        // Скалярное произведение векторов нестатическим методом
        System.out.println("Скалярное произведение векторов нестатическим методом: " + vector1.getVectorsDotProduct(vector2));

        // Инверсия вектора
        vector1.getVectorReversal();
        System.out.println("Инверсия вектора 1: " + vector1.toString());

        vector2.getVectorReversal();
        System.out.println("Инверсия вектора 2: " + vector2.toString());

        // Длина вектора
        System.out.println("Длина вектора 1: " + vector1.getVectorLength());
        System.out.println("Длина вектора 2: " + vector2.getVectorLength());

        // Получение и установка компоненты вектора по индексу
        vector1.getAndInstallComponent(0, 9);
        System.out.println(vector1.toString());

        // Переопределение метода equals, чтобы был true  векторы имеют одинаковую размерность и соответствующие компоненты равны. Переопределен хэш-код
        System.out.println(vector1.equals(vector2));

        // Результат получения нового вектора от суммы векторов статическим методом
        System.out.println("Результат получения нового вектора от суммы векторов статическим методом " + Vector.newSumVector(vector1, vector2));

        // Результат получения нового вектора от разности векторов статическим методом
        System.out.println("Результат получения нового вектора от разности векторов статическим методом" + Vector.newSubtractionVector(vector1, vector2));

        // Скалярное произведение векторов татическим методом
        System.out.println("Результат скалярного произведения векторов татическим методом " + Vector.getVectorsDotProduct(vector1, vector2));
    }
}
