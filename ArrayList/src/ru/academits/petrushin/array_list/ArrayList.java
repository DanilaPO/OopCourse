package ru.academits.petrushin.array_list;

import java.util.*;

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
            throw new IllegalArgumentException("Недопустимое значение вместимости, capacity < 0. Передано: " + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + (size - 1) + ". Указан индекс " + index);
        }
    }

    private void checkElementIndex(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size. Допустимый диапазон от 0 до " + size + ". Указан индекс " + index);
        }
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    // Удаление элемента remove
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index < 0) {
            return false;
        }

        remove(index);

        return true;
    }

    // Метод containsAll (проверка наличия элементов коллекций)
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    // Метод добавления всех элементов коллекции в список - addAll
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    // Метод добавления всех элементов коллекции в список по индексу - addAll
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkElementIndex(index, c);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size() + index);

        size += index + c.size();

        System.arraycopy(items, index, items, index + c.size(), size - index - c.size());

        size -= index;

        System.arraycopy(c.toArray(), 0, items, index, c.size());

        ++modCount;

        return true;
    }

    // Метод удаления всех элементов этой коллекции, которые также содержатся в указанной коллекции - removeAll
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

    // Метод сохранения в этой коллекции только тех элементов, которые содержатся в указанной коллекции - retainAll
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

    // Метод очистки списка - clear()
    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);

        size = 0;
        ++modCount;
    }

    // Метод получения элемента списка по индексу - get
    @Override
    public E get(int index) {
        checkBounds(index);

        return items[index];
    }

    // Замена элемента списка по индексу - set
    @Override
    public E set(int index, E item) {
        checkBounds(index);

        E oldItem = items[index];

        items[index] = item;

        return oldItem;
    }

    // Метод вставляет указанный элемент в указанную позицию в этом списке - add(int index, T element)
    @Override
    public void add(int index, E item) {
        checkElementIndex(index, item);

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;
        ++size;
        ++modCount;
    }

    // Удаление элемента по индексу - remove
    @Override
    public E remove(int index) {
        checkBounds(index);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        --size;
        ++modCount;

        return removedItem;
    }

    // Метод возвращает индекс первого вхождения указанного элемента в этом списке - indexOf
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(items[i], o) || items[i] == null && o == null) {
                return i;
            }
        }

        return -1;
    }

    // Метод возвращает индекс последнего вхождения указанного элемента в этом векторе - lastIndexOf()
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o) || items[i] == null && o == null) {
                return i;
            }
        }

        return -1;
    }

    // Метод contains(Object o) возвращает true, если этот набор содержит элемент
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
                throw new ConcurrentModificationException("В коллекции добавились/удалились элементы за время обхода");
            }

            if (currentIndex == size) {
                throw new NoSuchElementException ("Коллекция кончилась");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (size > array.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, items.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (size < array.length) {
            array[size] = null;
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

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");



        for (int i = 0; i < size; ++i) {
            stringBuilder.append(get(i))
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
            if (!Objects.equals(items[i], arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(Arrays.copyOf(items, size));

        return hash;
    }
}