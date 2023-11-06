package ru.academits.petrushin.hash_table;

import java.util.*;
import java.util.LinkedList;

public class HashTable<E> implements Collection<E> {
    private LinkedList<E>[] lists;
    private static final int length = 10;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = new LinkedList[length];
    }

    public HashTable(int length) {
        //noinspection unchecked
        lists = new LinkedList[length];
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
        for (LinkedList<E> list : lists) {
            if (list == null && o == null) {
                return true;
            }

            if (list != null && list.contains(o)) {
                return true;
            }
        }

        return false;
    }

    private class HashTableListIterator implements Iterator<E> {
        private int currentArrayIndex = -1;

        private final int originalModCount = modCount;

        public boolean hasNext() {
            return currentArrayIndex < size;
        }

        public E next() {
            if (currentArrayIndex >= size) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (originalModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            ++currentArrayIndex;

            for (LinkedList<E> list : lists) {
                if (list == null) {
                    continue;
                }

                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i) == null) {
                        continue;
                    }

                    return list.get(i);
                }
            }

            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];

        int indexPosition = 1;

        for (LinkedList<E> list : lists) {
            if (list == null) {
                continue;
            }

            System.arraycopy(list.toArray(), 0, newArray, indexPosition - 1, list.size());

            indexPosition += list.size();
        }

        return newArray;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив");
        }

        a = Arrays.copyOf(a, a.length + size);


        System.arraycopy(toArray(), 0, a, a.length - size, size);

        return a;
    }

    public boolean add(E data) {
        ++size;

        int index = hashTableIndex(data);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        lists[index].addFirst(data);

        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int hashTableIndex = hashTableIndex(o);

        if (lists[hashTableIndex] == null) {
            return false;
        }

        --size;

        return lists[hashTableIndex].remove(o);
    }

    // TODO: - логика неправильная, метод должен проверять наличие всех элементов коллекции в хэш-таблице, а не наоборот
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        for (Object element : c) {
            if (element == null) {
                continue;
            }

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

        boolean isRemoved = false;

        for (LinkedList<E> list : lists) {
            if (list == null) {
                continue;
            }

            isRemoved = list.removeAll(c);
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (LinkedList<E> list : lists) {
            if (list == null) {
                continue;
            }

            isRemoved = list.retainAll(c);
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);

        size = 0;

        ++modCount;
    }

    private int hashTableIndex(Object object) {
        int hash = 1;

        hash = hash + (object != null ? object.hashCode() : 0);

        return Math.abs(hash % lists.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        //noinspection rawtypes
        for (LinkedList list : lists) {
            stringBuilder.append(list)
                    .append(System.lineSeparator());
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        return stringBuilder.toString();
    }
}