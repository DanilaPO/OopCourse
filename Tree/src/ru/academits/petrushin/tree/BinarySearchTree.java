package ru.academits.petrushin.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private final Comparator<E> comparator;
    private TreeNode<E> root;
    private int size;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        comparator = null;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data2 == null) {
            return 1;
        }

        if (data1 == null) {
            return -1;
        }

        //noinspection unchecked
        return ((Comparable<E>) data1).compareTo(data2);
    }

    // Вставка узла
    public void add(E data) {
        ++size;

        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult <= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));

                    return;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));

                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    // Поиск узла
    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(currentNode.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    // Удаление первого вхождения
    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> removedNode = root;
        TreeNode<E> removedNodeParent = null;
        boolean isLeftChild = false;

        while (true) {
            int comparisonResult = compare(removedNode.getData(), data);

            if (comparisonResult > 0) {
                isLeftChild = true;
            } else if (comparisonResult < 0) {
                isLeftChild = false;
            } else {
                break;
            }

            removedNodeParent = removedNode;

            if (isLeftChild) {
                if (removedNode.getLeft() == null) {
                    return false;
                }

                removedNode = removedNode.getLeft();
            } else {
                if (removedNode.getRight() == null) {
                    return false;
                }

                removedNode = removedNode.getRight();
            }
        }

        --size;

        TreeNode<E> replacementNode;

        if (removedNode.getLeft() == null || removedNode.getRight() == null) {
            replacementNode = removedNode.getLeft() == null ? removedNode.getRight() : removedNode.getLeft();
        } else {
            TreeNode<E> leftmostNode = removedNode.getRight();

            if (leftmostNode.getLeft() == null) {
                leftmostNode.setLeft(removedNode.getLeft());
            } else {
                TreeNode<E> leftmostNodeParentNode = removedNode;

                while (leftmostNode.getLeft() != null) {
                    leftmostNodeParentNode = leftmostNode;
                    leftmostNode = leftmostNode.getLeft();
                }

                leftmostNodeParentNode.setLeft(leftmostNode.getRight());

                leftmostNode.setLeft(removedNode.getLeft());
                leftmostNode.setRight(removedNode.getRight());
            }

            replacementNode = leftmostNode;
        }

        if (removedNodeParent == null) {
            root = replacementNode;
        } else if (isLeftChild) {
            removedNodeParent.setLeft(replacementNode);
        } else {
            removedNodeParent.setRight(replacementNode);
        }

        return true;
    }

    // Получение числа элементов
    public int getSize() {
        return size;
    }

    // Обход в ширину
    public void visitInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.poll();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    // Обход в глубину без рекурсии
    public void visitInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.pop();

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    // Рекурсивный обход в глубину
    public void visitInDepthRecursively(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        visit(root, consumer);
    }

    private void visit(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        visit(node.getLeft(), consumer);
        visit(node.getRight(), consumer);
    }
}