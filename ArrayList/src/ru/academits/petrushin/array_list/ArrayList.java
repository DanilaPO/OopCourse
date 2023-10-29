package ru.academits.petrushin.array_list;

import java.util.*;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity <= 0. Передано " + capacity);
        } else {
            //noinspection unchecked
            items = (E[]) new Object[capacity];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }

        return true;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index < 0) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new IllegalStateException("Передана пустая коллекция");
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addAll(size, c);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }

        if (c.isEmpty()) {
            return false;
        }

        if (size == items.length || items.length <= size + c.size()) {
            items = Arrays.copyOf(items, size + c.size());
        }

        for (Object e : c) {
            //noinspection CastCanBeRemovedNarrowingVariableType
            add(index, (E) e);

            ++index;

            ++size;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new IllegalStateException("Передана пустая коллекция");
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; --i) {
            if (c.contains(items[i])) {
                remove(i);

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; --i) {
            if (!c.contains(items[i])) {
                remove(i);

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);

        size = 0;

        ++modCount;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива длинной " + (size - 1) + ". Передано " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива длинной " + (size - 1) + ". Передано " + index);
        }

        E oldItem = items[index];

        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы списка с диапозоном от 0 до " + size + ". Передан индекс " + index);
        }

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;

        ++size;

        ++modCount;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Выход за пределы массива, заполненная длина которого = " + (size - 1) + ". Передан индекс " + index);
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным. Передан индекс " + index);
        }

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        --size;
        ++modCount;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i] == null) {
                continue;
            }

            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i] == null) {
                continue;
            }

            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (currentIndex >= size) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return toArray(items);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (size > array.length) {
            //noinspection unchecked
            array = (T[]) Arrays.copyOf(items, size);
        }

        if (array[size] != null) {
            for (int i = size; i < array.length; ++i) {
                array[i] = null;
            }
        }

        System.arraycopy(items, 0, array, 0, size);

        return array;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public ListIterator<E> listIterator() {
        return null;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        for (E e : items) {
            if (e == null) {
                continue;
            }

            stringBuilder.append(e)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public void trimToSize() {
        if (size > items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[DEFAULT_CAPACITY];

            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        ArrayList<E> arrayList = (ArrayList<E>) o;

        if (size == arrayList.size) {
            for (int i = 0; i < size; ++i) {
                if (items[i] != arrayList.items[i]) {
                    return false;
                }
            }
        }

        return items != arrayList.items && size == arrayList.size;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;


        //noinspection unchecked
        E[] array = (E[]) new Object[items.length];

        int index = 0;

        for (E e : items) {
            if (e != null) {
                array[index] = e;

                ++index;
            }
        }

        hash = prime * hash + Arrays.hashCode(array);
        hash = prime * hash + size;

        return hash;
    }
}