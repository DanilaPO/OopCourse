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
        if (length <= 0) {
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
        int index = getIndex(o);

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(o);
    }

    private class HashTableListIterator implements Iterator<E> {
        private final int itemsNumber;
        private int currentArrayIndex;
        private int currentElementIndex = -1;
        private int visitedItemsNumber;

        private final int originalModCount = modCount;

        private HashTableListIterator(int itemsNumber) {
            this.itemsNumber = itemsNumber;
        }

        public boolean hasNext() {
            return visitedItemsNumber < itemsNumber;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (originalModCount != modCount) {
                throw new ConcurrentModificationException("В коллекции добавились/удалились элементы за время обхода");
            }

            ++visitedItemsNumber;

            ++currentElementIndex;

            while (lists[currentArrayIndex] == null || lists[currentArrayIndex].size() == 0 || currentElementIndex >= lists[currentArrayIndex].size()) {
                ++currentArrayIndex;
                currentElementIndex = 0;
            }

            return lists[currentArrayIndex].get(currentElementIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        int itemsNumber = 0;

        for (ArrayList<E> e : lists) {
            if (e == null) {
                continue;
            }

            itemsNumber += e.size();
        }

        return new HashTableListIterator(itemsNumber);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                array[i] = element;
                ++i;
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передан массив значения Null");
        }

        if (a.length <= size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(toArray(), size);
            return a;
        }

        //noinspection unchecked
        a = (T[]) Arrays.copyOf(toArray(), a.length);

        a[size] = null;

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

        if (lists[index].remove(o)) {
            ++modCount;
            --size;
        }

        return false;
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

        boolean removed = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            if (list.removeAll(c)) {
                removed = true;
                --size;
            }
        }

        if (removed) {
            int itemsNumber = 0;

            for (ArrayList<E> e : lists) {
                if (e == null) {
                    continue;
                }

                itemsNumber += e.size();
            }

            size = itemsNumber;

            ++modCount;
        }

        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0) {
            return false;
        }

        boolean retained = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            if (list.retainAll(c)) {
                retained = true;
                --size;
            }
        }

        if (retained) {
            int itemsNumber = 0;

            for (ArrayList<E> e : lists) {
                if (e == null) {
                    continue;
                }

                itemsNumber += e.size();
            }

            size = itemsNumber;

            ++modCount;
        }

        return retained;
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

        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ArrayList<E> list : lists) {
            stringBuilder.append(list)
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}