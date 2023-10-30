package ru.academits.petrushin.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    // копирование списка
    public SinglyLinkedList(SinglyLinkedList<E> list) {
        if (list == null) {
            throw new NullPointerException("Для копирования передан пустой список");
        }

        for (int i = list.getSize() - 1; i >= 0; --i) {
            addFirst(list.get(i));
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
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапазоном от 0 до " + (size - 1) + ". Передано " + index);
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

        --size;

        if (index == 0) {
            return removeFirst();
        }

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
        if (index > size + 1 || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапазоном от 0 до " + (size + 1) + ". Передано " + index);
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
        if (data == null) {
            return false;
        }

        for (ListItem<E> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
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
        if (isEmpty()) {
            return;
        }

        ListItem<E> last = null;


        for (ListItem<E> item = head, previous = null; item != null; previous = item, item = item.getNext()) {
            addFirst(item.getData());

            if (previous == null) {
                last = head;
            }
        }

        last.setNext(null);
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