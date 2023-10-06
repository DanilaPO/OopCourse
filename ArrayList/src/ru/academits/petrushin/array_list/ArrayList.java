package ru.academits.petrushin.array_list;

import java.util.*;
import java.util.ListIterator;

public class ArrayList<T> implements List<T>{
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    int n = 0;

    //  В список надо добавить поле modCount – число изменений,
    //и менять его во всех методах удаления и добавления
    //• При создании объекта итератора запоминать в поле
    //значение modCount из списка на тот момент, а потом
    //сравнивать с ним текущее значение
    private int modCount;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity <= 0");
        } else {
            list = (T[]) new Object[capacity];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T element) {
        if (size == list.length - 1) {
            increaseCapacity();
            list[size] = element;
        }

        list[size] = element;
        size++;
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index;
        boolean isContains = false;

        for (Iterator<?> i = iterator(); i.hasNext(); ) {
            Object element1 = i.next();

            if (element1.equals(o)) {
                index = indexOf(element1);

                System.arraycopy(list, index + 1, list, index, size - index - 1);

                list[size - 1] = null;
                --size;

                ++modCount;

                isContains = true;
            }
        }

        return isContains;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isContains = true;

        for (Iterator<?> i = iterator(); i.hasNext(); ) {
            Object element1 = i.next();

            if (!isContains) {
                return false;
            }

            for (Iterator<?> j = c.iterator(); j.hasNext(); ) {
                Object element2 = j.next();

                if (element1.equals(element2)) {
                    isContains = true;
                    break;
                } else {
                    isContains = false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
            Object element = i.next();
            add((T) element);
            ++modCount;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean isAdd = false;

        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (size == list.length - 1) {
            increaseCapacity();
        }

        if (index == size) {
            for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
                Object element = i.next();

                add((T) element);
                index++;

                isAdd = true;
            }

            if (isAdd == true) {
                ++modCount;
            }

            return isAdd;
        }

        for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
            Object element = i.next();

            add(index, (T) element);
            index++;

            isAdd = true;
        }

        if (isAdd == true) {
            ++modCount;
        }

        return isAdd;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;

        for (Iterator<?> i = iterator(); i.hasNext(); ) {
            Object element1 = i.next();

            for (Iterator<?> j = c.iterator(); j.hasNext(); ) {
                Object element2 = j.next();

                if (element1.equals(element2)) {
                    System.arraycopy(list, indexOf(element1) + 1, list, indexOf(element1), size - indexOf(element1) - 1);
                    list[size - 1] = null;
                    --size;
                    ++modCount;

                    isRemove = true;
                }
            }
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int count = 0;

        for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
            Object element1 = i.next();

            for (Iterator<?> j = iterator(); j.hasNext(); ) {
                Object element2 = j.next();

                if (element1.equals(element2)) {
                    ++count;
                }
            }
        }

        if (count == size - 1) {
            return false;
        }

        T[] newList = (T[]) new Object[count];

        int index = 0;

        for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
            Object element1 = i.next();

            for (Iterator<?> j = iterator(); j.hasNext(); ) {
                Object element2 = j.next();

                if (element1.equals(element2)) {
                    newList[index] = (T) element1;
                    ++index;
                }
            }
        }

        ++modCount;

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= size; ++i) {
            list[i] = null;
        }

        list = Arrays.copyOf(list, size());

        size = 0;

        ++modCount;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        return list[index];
    }

    @Override
    public T set(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (size == list.length - 1) {
            increaseCapacity();
        }

        list[index] = element;

        ++modCount;

        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (size == list.length - 1) {
            increaseCapacity();
        }

        if (index == size) {
            list[index] = element;
            return;
        }

        for (int i = size; i >= index; --i) {
            list[i + 1] = list[i];
        }

        list[index] = element;

        size++;

        ++modCount;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        T removeElement = list[index];

        System.arraycopy(list, index + 1, list, index, size - index - 1);

        list[size - 1] = null;
        --size;
        ++modCount;

        return removeElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (list[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (list[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (list[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int currentModCount;

        public MyListIterator(int currentModCount) {
            this.currentModCount = currentModCount;
        }

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (currentIndex >= size) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("в коллекции добавились/удалились элементы за время обхода");
            }

            ++currentIndex;
            return list[currentIndex];
        }
    }

    public Iterator<T> iterator() {
        int currentModCount = modCount;
        return new MyListIterator(currentModCount);
    }

    public Object[] toArray() {
        return list;
    }

    public <T> T[] toArray(T[] a) {
        trimToSize();

        Object[] list2 = new Object[size()];
        list2 = list;

        return (T[]) list;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public ListIterator<T> listIterator() {
        return null;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    // В Java методы sublist и listIterator реализовывать не нужно
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public String toString() {
        return Arrays.toString(list);
    }

    public void trimToSize() {
        list = Arrays.copyOf(list, size());
    }

    public void ensureCapacity(int capacity) {
        if (capacity > list.length) {
            list = Arrays.copyOf(list, capacity);
        }
    }

    public void increaseCapacity() {
        list = Arrays.copyOf(list, list.length * 2);
    }
}
