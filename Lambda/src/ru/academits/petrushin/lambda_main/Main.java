package ru.academits.petrushin.lambda_main;

import ru.academits.petrushin.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Иван", 15),
                new Person("Андрей", 21),
                new Person("Иван", 24),
                new Person("Павел", 17),
                new Person("Иван", 18),
                new Person("Андрей", 50));

        // А) получить список уникальных имен
        List<String> uniqueNamesList = personsList
                .stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println("a) Список уникальных имен: " + uniqueNamesList);

        // Б) вывести список уникальных имен в формате:"Имена: Иван, Сергей, Петр."
        String uniqueNamesString = uniqueNamesList
                .stream()
                .collect(Collectors.joining(", ", "б) Имена: ", "."));
        System.out.println(uniqueNamesString);

        // В) получить список людей младше 18, посчитать для них средний возраст
        List<Person> personsUnder18yearsList = personsList
                .stream()
                .filter(p -> p.getAge() < 18)
                .toList();
        System.out.print("в) Список людей младше 18: " + personsUnder18yearsList
                .stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")) + "- ");
        try {
            System.out.println("их средний возраст равен " + personsUnder18yearsList
                    .stream()
                    .mapToInt(Person::getAge)
                    .average()
                    .orElseThrow());
        } catch (NoSuchElementException e) {
            System.out.println("Передан пустой список");
        }

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        System.out.println("г) Map, в котором ключи – имена, а значения – средний возраст:");
        Map<String, Double> middleAgesByName = personsList
                .stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(middleAgesByName);

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        String certainAgePersonsList = personsList.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("д) список людей возраста от 20 до 45 в порядке возрастания: " + certainAgePersonsList);

        // Создать бесконечный поток корней чисел. С консоли прочитать число – сколько элементов нужно вычислить,
        // затем – распечатать эти элементы

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сколько элементов корней потока чисел нужно вычислить: ");
        int sequenceNumber = scanner.nextInt();

        DoubleStream roots = DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(sequenceNumber);
        roots.forEach(System.out::println);

        // *Попробовать реализовать бесконечный поток чисел Фибоначчи
        System.out.print("Введите сколько элементов из последовательности чисел Фибоначчи хотите получить: ");
        int FibonacciNumberSequenceElementsCount = scanner.nextInt();

        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .map(x -> x[0])
                .limit(FibonacciNumberSequenceElementsCount)
                .forEach(System.out::println);
    }
}