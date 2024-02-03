package ru.academits.petrushin.example1.main;

import ru.academits.petrushin.example1.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person("Иван", 15), new Person("Андрей", 21),
                new Person("Иван", 24), new Person("Павел", 17), new Person("Иван", 18),
                new Person("Андрей", 50));

        // А) получить список уникальных имен
        System.out.print("a) Список уникальных имен: ");
        list.stream().map(Person::getName).distinct().forEach(p -> System.out.print(p + " "));
        System.out.println();

        // Б) вывести список уникальных имен в формате:"Имена: Иван, Сергей, Петр."
        String uniqueNamesString = list.stream().map(Person::getName).distinct().collect(Collectors.joining(", "));
        System.out.print("б) Имена: " + uniqueNamesString + ".");
        System.out.println();

        // В) получить список людей младше 18, посчитать для них средний возраст
        List<Person> filteredList = list.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList());
        System.out.print("в) Список людей младше 18: " + filteredList.stream().map(Person::getName).collect(Collectors.
                joining(", ")) + "- ");
        System.out.print("их средний возраст равен " + filteredList.stream().mapToInt(Person::getAge).average());
        System.out.println();

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        System.out.println("г) Map, в котором ключи – имена, а значения – средний возраст:");
        Map<String, List<Person>> mapList = list.stream().collect(Collectors.groupingBy(Person::getName));
        mapList.forEach((name, p) -> System.out.printf("Имя %s: %s - средний возраст%n", name, p.stream().mapToInt(x ->
                x.getAge()).average()));

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        String nameList = list.stream().filter(p -> p.getAge() >= 20).filter(p -> p.getAge() < 45).sorted((p1, p2) -> p2.getAge() -
                - p1.getAge()).map(p -> p.getName()).collect(Collectors.joining(", "));
        System.out.print("д) список людей возраста от 20 до 45 в поярдке возрастания: " + nameList);
    }
}