package ru.academits.petrushin.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    // копирование списка
    public SinglyLinkedList(SinglyLinkedList<E> list) {
        if (list.getSize() == 0) {
            throw new NullPointerException("Для копирования передан пустой список");
        }

        addFirst(list.removeFirst());

        ListItem<E> curretnItem = head;

        int i = list.getSize();

        while (i > 0) {
            curretnItem.setNext(new ListItem<E>(list.removeFirst()));

            curretnItem = curretnItem.getNext();

            --i;
        }
    }

    // получение размера списка
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // получение значение первого элемента
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапазоном от 0 до " + (size - 1) + ". Передано индекс: " + index);
        }
    }

    // получение значения по указанному индексу
    public E get(int index) {
        checkIndex(index);

        ListItem<E> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item.getData();
    }

    // изменение значения по указанному индексу - пусть выдает старое значение
    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> item = head;
        E oldData = null;

        for (int i = 0; i <= index; ++i) {
            if (i == index) {
                oldData = item.getData();
                item.setData(data);

                break;
            }

            item = item.getNext();
        }

        return oldData;
    }

    // удаление элемента по индексу, пусть выдает значение элемента
    public E removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        --size;

        int i = 0;

        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        while (index != i) {
            previousItem = item;
            item = item.getNext();

            ++i;
        }

        previousItem.setNext(item.getNext());

        return item.getData();
    }

    // вставка элемента в начало
    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    // вставка элемента по индексу
    public void add(int index, E data) {
        if (index < 0 || index > size + 1) {
            throw new IndexOutOfBoundsException("Выход за допустимые пределы списка с диапазоном от 0 до " + (size + 1) + ". Передано " + index);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        int i = 1;

        while (index != i) {
            previousItem = item;
            item = item.getNext();

            ++i;
        }

        ++size;

        previousItem.setNext(new ListItem<>(data, item));
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    @SuppressWarnings("UnusedReturnValue")
    public boolean removeByValue(E data) {
        for (ListItem<E> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if ((data == null && item.getData() == null) || item.getData().equals(data)) {
                if (previousItem == null) {
                    removeFirst();

                    return true;
                }

                --size;

                previousItem.setNext(item.getNext());

                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }

        E removedData = head.getData();

        head = head.getNext();

        --size;

        return removedData;
    }

    // разворот списка за линейное время
    public void reverse() {
        if (isEmpty() || size == 1) {
            return;
        }

        ListItem<E> current = head;
        ListItem<E> previous = null;
        ListItem<E> next = null;

        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData())
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}