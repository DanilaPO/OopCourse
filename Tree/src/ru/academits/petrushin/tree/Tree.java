package ru.academits.petrushin.tree;

import ru.academits.petrushin.tree_comparator.TreeComparator;
import ru.academits.petrushin.tree_node.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree<E> {
    TreeNode<E> root;

    int size;

    // вставка узла
    public void add(E data) {
        ++size;

        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode currentNode = root;
        TreeComparator comparator = new TreeComparator();


        while (true) {
            if (comparator.compare(data, currentNode.getData()) > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));

                    currentNode = currentNode.getRight();

                    return;
                }

                currentNode = currentNode.getRight();
            }

            if (comparator.compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));

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

    // удаление первого вхождения
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

    // обход в ширину
    public void breadthFirst() {
        List<TreeNode<E>> list = new LinkedList<>();

        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.poll();

            list.add(currentNode);

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }

        for (TreeNode<E> node : list) {
            System.out.print(node.getData() + ", ");
        }
    }

    // обход в глубину без рекурсии
    public void depthFirst() {
        List<TreeNode<E>> list = new LinkedList<>();

        Deque<TreeNode<E>> stack = new LinkedList<>();

        while (root != null) {
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode<E> currentNode = stack.pop();

                list.add(currentNode);

                if (currentNode.getLeft() != null) {
                    stack.addFirst(currentNode.getLeft());

                    if (currentNode.getLeft().getRight() != null) {
                        stack.add(currentNode.getLeft().getRight());
                    }
                }
            }

            root = root.getRight();
        }

        int i = 0;

        for (TreeNode<E> node : list) {
            if (i == size - 1) {
                System.out.print(node.getData());

                return;
            }

            System.out.print(node.getData() + ", ");
            ++i;
        }
    }

    // рекурсивный обход в глубину
    public void recursionDepthFirst() {
        visit(root);
    }

    public void visit(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getData() + " ");

        visit(node.getLeft());

        visit(node.getRight());
    }
}
