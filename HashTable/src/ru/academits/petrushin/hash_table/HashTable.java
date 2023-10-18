package ru.academits.petrushin.hash_table;

import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private SinglyLinkedList<T>[] array;
    private static int size = 10;

    public HashTable() {
        this.array = new SinglyLinkedList[size];
        this.size = size;
    }

    public HashTable(int size) {
        this.array = new SinglyLinkedList[size];
        this.size = size;
    }

    public SinglyLinkedList<T>[] getArray() {
        return array;
    }

    public void setArray(SinglyLinkedList<T>[] array) {
        this.array = array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public void add(T data) {
        int index = Math.abs(hashCode(data) % size);

        if (array[index] == null) {
            array[index] = new SinglyLinkedList<>();
        }

        array[index].add(data);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public int hashCode(T data) {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + (data != null ? data.hashCode() : 0);

        return hash;
    }

    public void print() {
        for (SinglyLinkedList<T> e : array) {
            if (e != null) {
                e.print();
            } else {
                System.out.println("[Null]");
            }
        }
    }
}
