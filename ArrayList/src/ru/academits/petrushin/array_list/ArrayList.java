package ru.academits.petrushin.array_list;
import java.util.Arrays;

public class ArrayList<T> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity <= 0");
        } else {
            list = (T[]) new Object[capacity];
        }
    }

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        if (size >= list.length) {
            list = Arrays.copyOf(list, size * 2);
        }

        list[size] = item;
        size++;
    }

    public void delete(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы size");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }

        if (index < size - 1) {
            System.arraycopy(list, index + 1, list, index, size - index - 1);
        }

        list[size - 1] = null;
        --size;
    }

        @Override
    public String toString() {
        return Arrays.toString(list);
    }

//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public Object get(int index) {
//        if (index >= size) {
//            throw new IndexOutOfBoundsException("Выход за пределы size");
//        }
//
//        if (index < 0) {
//            throw new IllegalArgumentException("Индекс не может быть отрицательным");
//        }
//
//        return items[index];
//    }
//
//    public void set(int index, Object element) {
//        if (index >= size) {
//            throw new IndexOutOfBoundsException("Выход за пределы size");
//        }
//
//        if (index < 0) {
//            throw new IllegalArgumentException("Индекс не может быть отрицательным");
//        }
//
//        items[index] = element;
//    }
//
//    public void add(Object element) {
//        if (size >= items.length) {
//            increaseCapacity();
//        }
//
//        items[size] = element;
//        ++size;
//    }
//
//    public void increaseCapacity() {
//        items = Arrays.copyOf(items, items.length * 2);
//    }
//
//    public void remove(int index) {
//        if (index >= size) {
//            throw new IndexOutOfBoundsException("Выход за пределы size");
//        }
//
//        if (index < 0) {
//            throw new IllegalArgumentException("Индекс не может быть отрицательным");
//        }
//
//        if (index < size - 1) {
//            System.arraycopy(items, index + 1, items, index, size - index - 1);
//        }
//
//        items[size - 1] = null;
//        --size;
//    }
//
//    public void removeRange(int start, int end) {
//        if (start >= size || end >= size) {
//            throw new IndexOutOfBoundsException("Выход за пределы size");
//        }
//
//        if (start < 0 || end < 0) {
//            throw new IllegalArgumentException("Индекс не может быть отрицательным");
//        }
//
//        System.arraycopy(items, end, items, start, size - 1);
//
//        items[size - 1] = null;
//
//        trimToSize();
//    }
//
//    public void clear() {
//        for (int i = 0; i <= size; ++i) {
//            items[i] = null;
//        }
//
//        items = Arrays.copyOf(items, size());
//    }
//

//
//    public void trimToSize() {
//        items = Arrays.copyOf(items, size());
//    }
//
//    public int indexOf(Object element) {
//        for (int i = 0; i < size; ++i) {
//            if (items[i].equals(element)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }
//
//    public int lastIndexOf(Object element) {
//        for (int i = size - 1; i >= 0; --i) {
//            if (items[i].equals(element)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }
//
//    public boolean contains(Object element) {
//        for (int i = size - 1; i >= 0; --i) {
//            if (items[i].equals(element)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
