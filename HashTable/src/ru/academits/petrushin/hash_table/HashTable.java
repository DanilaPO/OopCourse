package ru.academits.petrushin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_LENGTH = 10;

    private final ArrayList<E>[] lists;

    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_LENGTH];
    }

    public HashTable(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Передано некорректное значение длины: " + length);
        }

        //noinspection unchecked
        lists = new ArrayList[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int strangeElementIndex = getIndex(o);

        if (lists[strangeElementIndex] == null && o != null) {
            return false;
        }

        if (lists[strangeElementIndex] == null && o == null) {
            return true;
        }

        if (lists[strangeElementIndex].size() == 0) {
            return false;
        }

        return lists[strangeElementIndex].contains(o);
    }

    private class HashTableListIterator implements Iterator<E> {
        private int currentArrayIndex = 0;
        private int currentElementIndex = -1;
        private int elementsCount = -1;

        private final int originalModCount = modCount;

        public boolean hasNext() {
            return currentArrayIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (originalModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            ++elementsCount;

            if (lists[currentArrayIndex] == null || elementsCount >= lists[currentArrayIndex].size() || lists[currentArrayIndex].size() == 0) {
                ++currentArrayIndex;
                currentElementIndex = -1;
                elementsCount = -1;
                return null;
            }

            ++currentElementIndex;

            return lists[currentArrayIndex].get(currentElementIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int elementIndex = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                array[elementIndex] = element;
                ++elementIndex;
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив");
        }

        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        if (a.length > size) {
            a[size] = null;
        }

        int elementIndex = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                //noinspection unchecked
                a[elementIndex] = (T) element;
                ++elementIndex;
            }
        }

        return a;
    }

    public boolean add(E data) {
        int index = getIndex(data);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(data);

        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] == null) {
            return false;
        }

        ++modCount;
        --size;

        return lists[index].remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана коллекция со значением Null");
        }

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        if (size == 0) {
            return false;
        }

        int removedCount = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            if (list.removeAll(c)) {
                list.removeAll(c);
                ++removedCount;
                ++modCount;
                --size;
            }
        }

        return removedCount > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0) {
            return false;
        }

        int retainedCount = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            if (list.retainAll(c)) {
                list.retainAll(c);
                ++retainedCount;
                ++modCount;
                --size;
            }
        }

        return retainedCount > 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        size = 0;

        ++modCount;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        int hash = object.hashCode();

        return Math.abs(hash % lists.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ArrayList<E> list : lists) {
            stringBuilder.append(list)
                    .append(System.lineSeparator());
        }

        stringBuilder.delete(stringBuilder.length(), stringBuilder.length());

        return stringBuilder.toString();
    }
}