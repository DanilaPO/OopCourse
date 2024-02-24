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
        if (list.getSize() == 0) {
            this.head = null;
        }

        String listString = list.toString();
        Object[] listItemsStrings = listString.substring(1, listString.length() - 1).split(", ");

        for (int i = listItemsStrings.length - 1; i >= 0; --i) {
            //noinspection unchecked
            addFirst((E) listItemsStrings[i]);
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
            throw new IndexOutOfBoundsException("Выход за за диапазон доустимых значений индексов списка от 0 до " + (size - 1) + ". Передан индекс: " + index);
        }
    }

    // получение значения по указанному индексу
    public E get(int index) {
        checkIndex(index);

        return getNode(index).getData();
    }

    // изменение значения по указанному индексу - пусть выдает старое значение
    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> currentItem = getNode(index);

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

        ListItem<E> previousItem = getNode(index - 1);
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
        if (index < 0 || index >= size + 1) {
            throw new IndexOutOfBoundsException("Выход за за диапазон доустимых значений индексов списка от 0 до " + (size + 1) + ". Передан " + index);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<E> previousItem = getNode(index - 1);
        ListItem<E> currentItem = previousItem.getNext();

        ++size;

        previousItem.setNext(new ListItem<>(data, currentItem));
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    @SuppressWarnings("UnusedReturnValue")
    public boolean removeByValue(E data) {
        for (ListItem<E> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if ((data == null && currentItem.getData() == null) || Objects.equals(currentItem.getData(), (data))) {
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
            ListItem<E> next = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = next;
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
    private ListItem<E> getNode(int index) {
        int i = 0;

        ListItem<E> node = null;

        for (ListItem<E> currentItem = head; currentItem != null; currentItem = currentItem.getNext(), ++i) {
            if (i == index) {
                node = currentItem;
                break;
            }
        }

        return node;
    }
}