package ru.academits.petrushin.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // получение размера списка
    public int getSize() {
        return size;
    }

    // получение значение первого элемента
    public T getFirstItem() {
        return head.getData();
    }

    // получение значения по указанному индексу
    public T getItem(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item.getData();
    }

    // изменение значения по указанному индексу - пусть выдает старое значение
    public T setItem(int index, T data) {
        if (index > size - 1) {
            throw new RuntimeException("Выход за пределы списка");
        }

        if (index < 0) {
            throw new RuntimeException("Индекс не может быть меньше 0");
        }

        ListItem<T> item = head;

        for (int i = 0; i < size; ++i) {
            if (i == index) {
                T previousItem = item.getData();
                item.setData(data);

                return previousItem;
            }

            item = item.getNext();
        }

        return null;
    }

    // удаление элемента по индексу, пусть выдает значение элемента
    public T remove(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (index == 0) {
            T item = head.getData();

            remove();

            return item;
        }

        ListItem<T> item = head;
        ListItem<T> previousItem = null;

        T itemData = item.getData();

        for (int i = 1; i < size; ++i) {
            if (i == index) {
                item = item.getNext();
                previousItem.setNext(item);

                --size;

                break;
            }
        }

        return itemData;
    }

    // вставка элемента в начало
    public void add(T data) {
        ListItem<T> item = new ListItem<>(data, head);
        head = item;

        ++size;
    }

    // вставка элемента по индексу
    public void add(int index, T data) {
        if (index > size - 1) {
            throw new RuntimeException("Выход за пределы списка");
        }

        if (index < 0) {
            throw new RuntimeException("Индекс не может быть меньше 0");
        }

        if (index == 0) {
            add(data);
            return;
        }

        ListItem<T> item = head;
        ListItem<T> previousItem = null;
        ListItem<T> newItem = new ListItem<T>(data);

        for (int i = 0; i < size; ++i) {
            if (i == index) {
                previousItem.setNext(newItem);
                newItem.setNext(item);
            }

            previousItem = item;
            item = item.getNext();
        }

        ++size;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean remove(T data) {
        for (ListItem<T> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData() == data) {
                if (data == getFirstItem()) {
                    remove();

                    return true;
                }

                item = item.getNext();
                previousItem.setNext(item);

                --size;

                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public T remove() {
        head = head.getNext();

        --size;

        return getFirstItem();
    }

    // разворот списка за линейное время
    public void reverse() {
        SinglyLinkedList<T> list = new SinglyLinkedList<T>();

        for (ListItem<T> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            list.add(item.getData());
        }

        head = list.getHead();
    }

    // копирование списка
    public SinglyLinkedList(SinglyLinkedList<T> list) {
        this.head = new ListItem<T>(list.getHead().getData(), list.getHead().getNext());
        this.size = list.getSize();
    }

    public void print() {
        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            System.out.print(item.getData() + " ");
        }

        System.out.println();
    }
}
