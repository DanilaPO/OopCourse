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
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимое значение вместимости, capacity < 0. Передано " + capacity);
        }
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }
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
            return false;
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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        if (items.length <= size + c.size()) {
            ensureCapacity(size + c.size());
        }

        // TODO: - здесь неэффективно использовать add
        for (Object e : c) {
            //noinspection CastCanBeRemovedNarrowingVariableType,unchecked
            add(index, (E) e);

            ++index;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
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
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size - 1, null);

        size = 0;

        ++modCount;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];

        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndex(index);

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
        checkIndex(index);

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
            if (items[i].equals(o) || items[i] == null) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i].equals(o) || items[i] == null) {
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
        return items;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (size > array.length) {
            //noinspection unchecked
            return Arrays.copyOfRange((T[]) items, 0, size);
        }

        if (!isEmpty()) {
            Arrays.fill(array, null);
        }

        //noinspection SuspiciousSystemArraycopy
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
        if (isEmpty() || items.length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        for (E e : items) {
            if (e == null) {
                break;
            }

            stringBuilder.append(e)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        stringBuilder.setCharAt(stringBuilder.length() - 1, ']');

        return stringBuilder.toString();
    }

    public void trimToSize() {
        if (size < items.length) {
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

        if (size != arrayList.size) {
            return false;
        }

        for (int i = 0; i < size; ++i) {
            if (!items[i].equals(arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(Arrays.copyOfRange(items, 0, size));
        hash = prime * hash + size;

        return hash;
    }
}