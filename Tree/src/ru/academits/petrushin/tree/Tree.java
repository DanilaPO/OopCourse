package ru.academits.petrushin.tree;

import ru.academits.petrushin.tree_node.TreeNode;
import ru.academits.petrushin.tree_comparator.TreeComparator;
public class Tree<E> {
    TreeNode root;

    int size;

    // вставка узла
    public void add(E data) {
        ++size;

        if (root == null) {
            root = new TreeNode(data);
            return;
        }

        TreeNode currentNode = root;
        TreeComparator comparator = new TreeComparator();


        while (true) {
            if (comparator.compare(data, currentNode.getData()) > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode(data));

                    currentNode = currentNode.getRight();

                    return;
                }

                currentNode = currentNode.getRight();
            }

            if (comparator.compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode(data));

                    currentNode = currentNode.getLeft();

                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    // поиск узла
    public boolean isInsight(E data) {
        if (root.getData().equals(data)) {
            return true;
        }

        TreeComparator comparator = new TreeComparator();
        TreeNode currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                return true;
            }

            while (comparator.compare(data, currentNode.getData()) > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }

            while (comparator.compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    public boolean removeFirst() {
        --size;

        if (root.getLeft() == null && root.getRight() == null) {
            root = null;

            return true;
        }

        if (root.getLeft() == null && root.getRight() != null) {
            root = root.getRight();

            return true;
        }

        if (root.getRight() == null) {
            TreeNode currentNode = root.getLeft();

            if (currentNode.getRight() != null) {
                root = currentNode.getRight();

                TreeNode tailNode = root;

                while (tailNode.getLeft() != null) {
                    tailNode = tailNode.getLeft();
                }

                tailNode.setLeft(currentNode);

                return true;
            }

            root = root.getLeft();

            return true;
        }

        TreeNode leftNode = root.getLeft();

        root = root.getRight();

        TreeNode currentNode = root;

        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        currentNode.setLeft(leftNode);

        return true;
    }

    // удаление заданного элемента (сделал дополнитеьно для себя)
    public boolean remove(E data) {
        if (!isInsight(data)) {
            return false;
        }

        TreeComparator comparator = new TreeComparator();

        if (root.getData().equals(data)) {
            removeFirst();

            return true;
        }

        --size;

        TreeNode currentNode = root;
        TreeNode previousNode = null;

        while (comparator.compare(data, currentNode.getData()) != 0) {
            if (comparator.compare(data, currentNode.getData()) > 0) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            }

            if (comparator.compare(data, currentNode.getData()) < 0) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }
        }

        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                if (currentNode.equals(previousNode.getRight())) {
                    currentNode = null;
                    previousNode.setRight(currentNode);

                    return true;
                }

                if (currentNode.equals(previousNode.getLeft())) {
                    currentNode = null;
                    previousNode.setLeft(currentNode);

                    return true;
                }
            }

            if (currentNode.getRight() == null) {
                currentNode = currentNode.getLeft();
                previousNode.setRight(currentNode);

                return true;
            }

            if (currentNode.getLeft() == null) {
                currentNode = currentNode.getRight();
                previousNode.setRight(currentNode);

                return true;
            }
        }

        TreeNode leftBranch = currentNode.getLeft();

        currentNode = currentNode.getRight();

        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        currentNode.setLeft(leftBranch);

        previousNode.setRight(previousNode.getRight().getRight());

        return true;
    }

    // получение числа элементов
    public int getSize() {
        return size;
    }

//    // обход в ширину
//    public void  breadthFirst() {
//        Queue queue = new LikedList();
//    }


    public void print() {
        TreeNode currentNode = root;

        System.out.print(currentNode.getData() + " ");

        currentNode = currentNode.getLeft();
        System.out.print(currentNode.getData() + " ");

        currentNode = currentNode.getLeft();
        System.out.print(currentNode.getData() + " ");

//        currentNode = currentNode.getLeft();
//        System.out.print(currentNode.getData() + " ");
//
//        currentNode = currentNode.getLeft();
//        System.out.print(currentNode.getData() + " ");

        // while (currentNode != null) {
        //     System.out.print(currentNode.getData() + " ");

        //     currentNode = currentNode.getLeft();
        // }

        // System.out.println();

        // currentNode = root;

        // while (currentNode != null) {
        //     System.out.print(currentNode.getData() + " ");

        //     currentNode = currentNode.getRight();
        // }
    }
}
