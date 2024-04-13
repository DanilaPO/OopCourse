package ru.academits.petrushin.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private Comparator<E> comparator;
    private int size;

    private int compare(E nodeData1, E nodeData2) {
        if (nodeData1 == null || nodeData2 == null) {
            if (nodeData1 == null && nodeData2 == null) {
                return 0;
            }

            return -1;
        }

        if (comparator == null) {
            //noinspection unchecked
            Comparable<E> givenTypeNode1 = (Comparable<E>) nodeData1;

            return givenTypeNode1.compareTo(nodeData2);
        }

        return comparator.compare(nodeData1, nodeData2);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
    }

    // Вставка узла
    public void add(E data) {
        ++size;

        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> newNode = new TreeNode<>(data);

        while (true) {
            int comparisonResult = compare(currentNode.getData(), newNode.getData());

            if (comparisonResult <= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);

                    return;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);

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
        TreeNode<E> newNode = new TreeNode<>(data);

        while (currentNode != null) {
            int comparisonResult = compare(currentNode.getData(), newNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult <= 0) {
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

        TreeNode<E> remoteNode = root;
        TreeNode<E> remoteNodeParent = null;
        TreeNode<E> newNode = new TreeNode<>(data);

        while (true) {
            int comparisonResult = compare(remoteNode.getData(), newNode.getData());

            if (comparisonResult == 0) {
                break;
            }

            if (comparisonResult < 0) {
                if (remoteNode.getRight() == null) {
                    return false;
                }

                remoteNodeParent = remoteNode;
                remoteNode = remoteNode.getRight();
            } else {
                if (remoteNode.getLeft() == null) {
                    return false;
                }

                remoteNodeParent = remoteNode;
                remoteNode = remoteNode.getLeft();
            }
        }

        --size;

        if (remoteNode.getLeft() == null || remoteNode.getRight() == null) {
            int comparisonResult = compare(remoteNode.getData(), remoteNodeParent.getData());

            if (remoteNode.getLeft() == null && remoteNode.getRight() == null) {
                if (remoteNodeParent == null) {
                    root = null;
                } else if (comparisonResult < 0) {
                    remoteNodeParent.setLeft(null);
                } else {
                    remoteNodeParent.setRight(null);
                }
            } else if (remoteNode.getRight() == null) {
                if (remoteNodeParent == null) {
                    root = remoteNode.getLeft();
                } else if (comparisonResult < 0) {
                    remoteNodeParent.setLeft(remoteNode.getLeft());
                } else {
                    remoteNodeParent.setRight(remoteNode.getLeft());
                }
            } else {
                if (remoteNodeParent == null) {
                    root = remoteNode.getRight();
                } else if (comparisonResult < 0) {
                    remoteNodeParent.setLeft(remoteNode.getRight());
                } else {
                    remoteNodeParent.setRight(remoteNode.getRight());
                }
            }

            return true;
        }

        TreeNode<E> parent = remoteNode;
        TreeNode<E> child = remoteNode.getRight();

        if (child.getLeft() == null) {
            if (remoteNodeParent == null) {
                root = remoteNode;
            } else if (compare(remoteNode.getRight().getData(), remoteNodeParent.getData()) >= 0) {
                remoteNode.getRight().setLeft(remoteNodeParent.getRight().getLeft());
                remoteNodeParent.setRight(remoteNode.getRight());
            } else {
                remoteNode.getRight().setLeft(remoteNodeParent.getLeft().getLeft());
                remoteNodeParent.setLeft(remoteNode.getRight());
            }
        } else {
            while (child.getLeft() != null) {
                parent = child;
                child = child.getLeft();
            }

            parent.setLeft(child.getRight());

            if (remoteNodeParent == null) {
                child.setLeft(root.getLeft());
                child.setRight(root.getRight());
                root = child;
            } else if (compare(remoteNode.getRight().getData(), remoteNodeParent.getData()) >= 0) {
                child.setLeft(remoteNodeParent.getRight().getLeft());
                child.setRight(remoteNodeParent.getRight().getRight());

                remoteNodeParent.setRight(child);
            } else {
                child.setLeft(remoteNodeParent.getLeft().getLeft());
                child.setRight(remoteNodeParent.getLeft().getRight());

                remoteNodeParent.setLeft(child);
            }
        }

        return true;
    }

    // Получение числа элементов
    public int getSize() {
        return size;
    }

    // Обход в ширину
    public void goInWidth(Consumer<E> consumer) {
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
    public void goInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> stackCurrentNode = stack.pollFirst();

            consumer.accept(stackCurrentNode.getData());

            if (stackCurrentNode.getRight() != null) {
                stack.addFirst(stackCurrentNode.getRight());
            }

            if (stackCurrentNode.getLeft() != null) {
                stack.addFirst(stackCurrentNode.getLeft());
            }
        }
    }

    // Рекурсивный обход в глубину
    public void goInDepthRecursively(Consumer<E> consumer) {
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