package ru.academits.petrushin.list;

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

        head = new ListItem<>(list.getHead());
        size = list.getSize();

        for (int i = 1; i < list.getSize(); ++i) {
            add(i, new ListItem<>(list.get(i)).getData());
        }
    }

    public E getHead() {
        return head.getData();
    }

    public void setHead(ListItem<E> head) {
        this.head.setData(head.getData());
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
            throw new NullPointerException("Список пуст");
        }

        return head.getData();
    }

    boolean checkIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапазоном от 0 до " + (size - 1) + ". Передано " + index);
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным. Передано " + index);
        }

        return true;
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

        for (int i = 0; i < size; ++i) {
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
    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            E deletedData = head.getData();

            removeFirst();

            return deletedData;
        }

        E deletedData = null;
        int count = 0;

        for (ListItem<E> currentNode = head, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (index == count) {
                deletedData = currentNode.getData();
                previousNode.setNext(currentNode.getNext());

                --size;

                break;
            }

            ++count;
        }

        return deletedData;
    }

    // вставка элемента в начало
    public void addFirst(E data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    // вставка элемента по индексу
    public void add(int index, E data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапазоном от 0 до " + (size) + ". Передано " + index);
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным. Передано " + index);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<E> currentNode = head;
        ListItem<E> previousNode = null;

        for (int i = 0; i <= size; ++i) {
            if (i == index) {
                previousNode.setNext(new ListItem<>(data, currentNode));
                break;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        ++size;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    @SuppressWarnings("UnusedReturnValue")
    public boolean deleteByValue(E data) {
        for (ListItem<E> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
                if (previousItem == null) {
                    removeFirst();

                    return true;
                }

                previousItem.setNext(item.getNext());

                --size;

                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFirst() {
        if (isEmpty()) {
            throw new NullPointerException("Список пуст");
        }

        E removedItem = getFirst();

        head = head.getNext();

        --size;

        return removedItem;
    }

    // разворот списка за линейное время
    public void reverse() {
        if (isEmpty()) {
            throw new NullPointerException("Список пуст");
        }

        for (int i = 0; i < size; ++i) {
            add((size - 1 - i), removeFirst());
        }
    }

    @Override
    public String toString() {
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