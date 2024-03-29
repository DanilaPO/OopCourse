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

        return lists[index].contains(o);
    }

    private class HashTableListIterator implements Iterator<E> {
        private int arrayIndex;
        private int elementIndex = -1;
        private int visitedItemsCount;

        private final int originalModCount = modCount;


        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (originalModCount != modCount) {
                throw new ConcurrentModificationException("В коллекции добавились/удалились элементы за время обхода");
            }

            ++visitedItemsCount;

            ++elementIndex;

            while (lists[arrayIndex] == null || elementIndex >= lists[arrayIndex].size()) {
                ++arrayIndex;
                elementIndex = 0;
            }

            return lists[arrayIndex].get(elementIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableListIterator();
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
        if (a.length < size) {
            //noinspection unchecked
            return Arrays.copyOf(toArray(), size, (Class<? extends T[]>) lists.getClass());
        }

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
        if (c.size() == 0 || size == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            int originalListSize = list.size();

            if (list.removeAll(c)) {
                isRemoved = true;
                size -= originalListSize - list.size();
            }
        }

        ++modCount;

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0) {
            return false;
        }

        boolean isRetained = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            int originalListSize = list.size();

            if (list.retainAll(c)) {
                isRetained = true;
                size -= originalListSize - list.size();
            }
        }

        ++modCount;

        return isRetained;
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