package ru.academits.petrushin.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private final Comparator<E> comparator;
    private int size;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        comparator = null;
    }

    private int compare(E data1, E data2) {
        if (comparator == null) {
            if (data1 == null || data2 == null) {
                if (data1 != null) {
                    return 1;
                }

                if (data2 == null) {
                    return 0;
                }

                return -1;
            }

            //noinspection unchecked
            return ((Comparable<E>) data1).compareTo(data2);
        }

        return comparator.compare(data1, data2);
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
        boolean isLeftNode;

        while (true) {
            if (compare(removedNode.getData(), data) == 0) {
                break;
            }

            isLeftNode = true;

            if (compare(removedNode.getData(), data) < 0) {
                isLeftNode = false;
            }

            removedNodeParent = removedNode;

            if (!isLeftNode) {
                if (removedNode.getRight() == null) {
                    return false;
                }

                removedNode = removedNode.getRight();
            } else {
                if (removedNode.getLeft() == null) {
                    return false;
                }

                removedNode = removedNode.getLeft();
            }
        }

        --size;

        if (removedNode.getLeft() == null || removedNode.getRight() == null) {
            if (removedNodeParent != null) {
                if (compare(removedNode.getData(), removedNodeParent.getData()) < 0) {
                    isLeftNode = true;
                } else {
                    isLeftNode = false;
                }
            } else {
                isLeftNode = false;
            }

            TreeNode<E> replacementNode;

            if (removedNode.getLeft() == null && removedNode.getRight() == null) {
                replacementNode = null;
            } else if (removedNode.getRight() == null) {
                replacementNode = removedNode.getLeft();
            } else {
                replacementNode = removedNode.getRight();
            }

            if (removedNodeParent == null) {
                root = replacementNode;
            } else if (isLeftNode) {
                removedNodeParent.setLeft(replacementNode);
            } else {
                removedNodeParent.setRight(replacementNode);
            }

            return true;
        }

        if (removedNodeParent != null && removedNode.getRight().getLeft() == null) {
            removedNode.getRight().setLeft(removedNode.getLeft());

            if (removedNode == removedNodeParent.getRight()) {
                removedNodeParent.setRight(removedNode.getRight());
            }

            if (removedNode == removedNodeParent.getLeft()) {
                removedNodeParent.setLeft(removedNode.getRight());
            }

            return true;
        }

        TreeNode<E> parentNode = removedNode;
        TreeNode<E> leftmostNode = removedNode.getRight();

        while (leftmostNode.getLeft() != null) {
            parentNode = leftmostNode;
            leftmostNode = leftmostNode.getLeft();
        }

        parentNode.setLeft(leftmostNode.getRight());

        leftmostNode.setLeft(removedNode.getLeft());
        leftmostNode.setRight(removedNode.getRight());

        if (removedNodeParent == null) {
            root = leftmostNode;
        } else if (removedNode == removedNodeParent.getRight()) {
            removedNodeParent.setRight(leftmostNode);
        } else {
            removedNodeParent.setLeft(leftmostNode);
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