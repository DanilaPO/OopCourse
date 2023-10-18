package ru.academits.petrushin.array_list;

import java.util.*;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] itemsArray;
    private int size;

    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        itemsArray = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity < 0. Передано " + capacity);
        } else {
            //noinspection unchecked
            itemsArray = (E[]) new Object[capacity];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        ++size;

        if (size == itemsArray.length - 1) {
            increaseCapacity();
        }

        ++modCount;

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
        if (c == null) {
            return false;
        }

        for (int i = 0; i < size; ++i) {

            if (!c.contains(get(i))) {
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
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }

        if (c.size() == 0) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        if (size == itemsArray.length - 1 || size <= itemsArray.length - 1 + c.size() - 1) {
            increaseCapacity();
        }

        System.arraycopy(itemsArray, index, itemsArray, index + c.size(), size());
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, itemsArray, index, c.size());

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        //noinspection SlowListContainsAll
        if (containsAll(c)) {
            clear();

            return true;
        }

        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
            if (c.contains(itemsArray[i])) {
                remove(i);

                --i;

                ++modCount;

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(itemsArray[i])) {
                remove(i);

                --i;

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (itemsArray == null) {
            return;
        }

        Arrays.fill(itemsArray, null);

        size = 0;

        ++modCount;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы массива длинной " + (size - 1) + ". Передано " + index);
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        return itemsArray[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        itemsArray[index] = element;

        return element;
    }

    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (size == itemsArray.length - 1) {
            increaseCapacity();
        }

        System.arraycopy(itemsArray, index, itemsArray, index + 1, size - index);

        itemsArray[index] = element;

        ++modCount;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Выход за пределы массива, заполнеенная длина которого = " + (size - 1) + ". Передан индекс " + index);
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным. Передан индекс " + index);
        }

        E removedElement = itemsArray[index];

        System.arraycopy(itemsArray, index + 1, itemsArray, index, size - index - 1);

        itemsArray[size - 1] = null;
        --size;
        ++modCount;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (itemsArray[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (itemsArray[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1) {
            return true;
        }

        return false;
    }

    private class ThisListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int elementaryModCount = modCount;

        public ThisListIterator() {
        }

        public boolean hasNext() {
            if (currentIndex >= size) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (elementaryModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            return currentIndex + 1 < size;
        }

        public E next() {
            return null;
        }
    }

    public Iterator<E> iterator() {
        return new ThisListIterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[0];

        //noinspection unchecked
        return (E[]) toArray(array);
    }

    public <E> E[] toArray(E[] array) {
        if (size <= array.length) {
            System.arraycopy(this.itemsArray, 0, array, 0, size);
        }

        if (size > array.length) {
            array = Arrays.copyOf(array, size);

            System.arraycopy(this.itemsArray, 0, array, 0, size);
        }

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

    public String toString() {
        if (size == 0) {
            return "Массив пуст";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        for (E e : itemsArray) {
            if (e == null) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

                stringBuilder.append("]");

                return stringBuilder.toString();
            }

            stringBuilder.append(e)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public void trimToSize() {
        if (size <= itemsArray.length) {
            return;
        }

        itemsArray = Arrays.copyOf(itemsArray, size());
    }

    public void ensureCapacity(int capacity) {
        if (capacity > itemsArray.length) {
            itemsArray = Arrays.copyOf(itemsArray, capacity);
        }
    }

    private void increaseCapacity() {
        if (itemsArray.length == 0) {
            itemsArray = Arrays.copyOf(itemsArray, 2);
        }

        itemsArray = Arrays.copyOf(itemsArray, itemsArray.length * 2);
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

        return itemsArray == arrayList.itemsArray && size == arrayList.size && modCount == arrayList.modCount;
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + DEFAULT_CAPACITY;
        hash = prime * hash + Arrays.hashCode(itemsArray);
        hash = prime * hash + size;
        hash = prime * hash + modCount;

        return hash;
    }
}
