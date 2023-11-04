package ru.academits.petrushin.tree_comparator;

import java.util.Comparator;

public class TreeComparator implements Comparator<Object> {
    @Override
    public int compare(Object node1, Object node2) {
        if (node1 instanceof Double) {
            return Double.compare((Double) node1, (Double) node2);
        }

        if (node1 instanceof Integer) {
            return Integer.compare((Integer) node1, (Integer) node2);
        }

        return -1;
    }
}