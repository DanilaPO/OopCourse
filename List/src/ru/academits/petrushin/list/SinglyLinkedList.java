package ru.academits.petrushin.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    // копирование списка
    public SinglyLinkedList(SinglyLinkedList<E> list) {
        this.size = list.size;

        if (list.size == 0) {
            return;
        }

        ListItem<E> item = new ListItem<>(list.head.getData());
        head = item;
        ListItem<E> newListItem = head;

        for (ListItem<E> originalListItem = list.head.getNext(); originalListItem != null; originalListItem = originalListItem.getNext()) {
            item = new ListItem<>(originalListItem.getData());
            newListItem.setNext(item);
            newListItem = newListItem.getNext();
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
            throw new IndexOutOfBoundsException("Выход за за диапазон допустимых значений индексов списка от 0 до " + (size - 1) + ". Передан индекс: " + index);
        }
    }

    // получение значения по указанному индексу
    public E get(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    // изменение значения по указанному индексу - пусть выдает старое значение
    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> currentItem = getItem(index);

        E oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    // удаление элемента по индексу, пусть выдает значение элемента
    public E removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        --size;

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> currentItem = previousItem.getNext();

        previousItem.setNext(currentItem.getNext());

        return currentItem.getData();
    }

    // вставка элемента в начало
    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    // вставка элемента по индексу
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за за диапазон допустимых значений индексов списка от 0 до " + size + ". Передан " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        ++size;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean removeByValue(E data) {
        for (ListItem<E> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
                if (previousItem == null) {
                    removeFirst();
                    return true;
                }

                --size;

                previousItem.setNext(currentItem.getNext());

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
        if (size <= 1) {
            return;
        }

        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
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

    // вспомогательный метод итерирования до узла
    public ListItem<E> getItem(int index) {
        ListItem<E> currentItem = head;

        for (int i = 1; i <= index; ++i) {
            currentItem = currentItem.getNext();

        }

        return currentItem;
    }
}