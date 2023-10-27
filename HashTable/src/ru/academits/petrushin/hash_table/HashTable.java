package ru.academits.petrushin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private SinglyLinkedList<E>[] array;
    private static int size = 10;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        this.array = new SinglyLinkedList[size];
        //noinspection AccessStaticViaInstance
        this.size = size;
    }

    public HashTable(int size) {
        //noinspection unchecked
        this.array = new SinglyLinkedList[size];
        //noinspection AccessStaticViaInstance
        this.size = size;
    }

    public SinglyLinkedList<E>[] getArray() {
        return array;
    }

    public void setArray(SinglyLinkedList<E>[] array) {
        this.array = array;
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
        if (array == null) {
            return false;
        }

        int index = Math.abs(hashCode(o) % size);

        if (array[index] == null) {
            return false;
        }

        for (int i = 0; i < array[index].getSize(); ++i) {
            if (o.equals(array[index].get(i))) {
                return true;
            }
        }

        return false;
    }

    private class ThisListIterator implements Iterator<E> {
        private int currentArrayIndex = -1;

        private final int elementaryModCount = modCount;

        @SuppressWarnings("unchecked")
        E[] currentArray = (E[]) toArray();

        public boolean hasNext() {
            if (currentArrayIndex >= currentArray.length) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (elementaryModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            if (currentArrayIndex >= 0 && currentArray[currentArrayIndex] == null) {
                return false;
            }

            return currentArrayIndex + 1 < currentArray.length;
        }

        // TODO
        public E next() {
            ++currentArrayIndex;

            return currentArray[currentArrayIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ThisListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[0];

        return toArray(newArray);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив");
        }

        int count = 0;

        for (int i = 0; i < size; ++i) {
            if (array[i] == null) {
                continue;
            }

            count += array[i].getSize();
        }

        if (count > a.length) {
            a = Arrays.copyOf(a, count);
        }

        int currentArrayIndex = 0;

        for (int i = 0; i < size; ++i) {
            if (array[i] != null && array[i].getSize() != 0) {
                for (int j = 0; j < array[i].getSize(); ++j) {
                    //noinspection unchecked,ReassignedVariable
                    a[currentArrayIndex] = (E) array[i].get(j);

                    ++currentArrayIndex;
                }
            }
        }

        return a;
    }

    public boolean add(E data) {
        int index = Math.abs(hashCode(data) % size);

        if (array[index] == null) {
            array[index] = new SinglyLinkedList<>();
        }

        array[index].addFirst(data);

        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }

        int index = Math.abs(hashCode(o) % size);

        for (int i = 0; i < array[index].getSize(); ++i) {
            if (o.equals(array[index].get(i))) {
                if (array[index].getSize() > 1) {
                    array[index].deleteByIndex(i);
                } else {
                    array[index].set(i, null);
                }

                ++modCount;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if ((c == null) || (array == null)) {
            return false;
        }

        for (int i = 0; i < size; ++i) {
            if (array[i] == null) {
                continue;
            }

            for (int j = 0; j < array[i].getSize(); ++j) {
                if (!c.contains(array[i].get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        if (containsAll(c)) {
            throw new RuntimeException("Хэш-таблица уже имеет все элементы переданной коллекции");
        }

        for (E e : c) {
            if (contains(e)) {
            } else {
                add(e);
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            throw new NullPointerException("Передана пустая коллекция");
        }

        if (containsAll(c)) {
            clear();

            return true;
        }

        boolean isRemoved = false;

        for (Object e : c) {
            if (contains(e)) {
                remove(e);

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

        Object[] newArray = new Object[0];

        newArray = toArray(newArray);

        boolean isRemoved = false;

        for (Object e : newArray) {
            if (!c.contains(e)) {
                remove(e);

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            array[i] = null;
        }
    }

    public int hashCode(Object data) {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + (data != null ? data.hashCode() : 0);

        return hash;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        HashTable<E> hashTableArray = (HashTable<E>) o;

        return array == hashTableArray.array && modCount == hashTableArray.modCount;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;

        //noinspection rawtypes
        for (SinglyLinkedList e : array) {
            if (i != size - 1) {
                if (e != null) {
                    stringBuilder.append(e)
                            .append(System.lineSeparator());
                } else {
                    stringBuilder.append("[Null]")
                            .append(System.lineSeparator());
                }

                ++i;
            } else {
                if (e != null) {
                    stringBuilder.append(e);
                } else {
                    stringBuilder.append("[Null]");
                }
            }
        }

        return stringBuilder.toString();
    }
}