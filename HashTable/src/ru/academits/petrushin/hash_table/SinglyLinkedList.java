package ru.academits.petrushin.hash_table;

class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        this.head = head;
        this.count = count;
    }

    // получение значения по указанному индексу
    public T getItem(int index) {
        if (index > count) {
            throw new RuntimeException("Выход за пределы списка");
        }

        if (index < 0) {
            throw new RuntimeException("Индекс не может быть меньше 0");
        }

        ListItem<T> item = head;

        for (int i = 0; i < count; ++i) {
            if (i == index) {
                return item.getData();
            }

            item = item.getNext();
        }

        return null;
    }

    // вставка элемента в начало
    public void add(T data) {
        ListItem<T> p = new ListItem<>(data, head);
        head = p;

        ++count;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public T remove() {
        T firstItem = head.getData();

        head = head.getNext();

        return firstItem;
    }

    public void print() {
        System.out.print("[");

        for (ListItem<T> newItem = head; newItem != null; newItem = newItem.getNext()) {
            System.out.print(newItem.getData());

            if (newItem.getNext() != null) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}