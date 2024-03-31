package ru.academits.petrushin.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class SearchingBinaryTree<E> implements Comparator<TreeNode<E>> {
    private TreeNode<E> root;
    private Comparator<TreeNode<E>> comparator;
    int size;

    public SearchingBinaryTree(Comparator<TreeNode<E>> comparator) {
        this.comparator = comparator;
    }

    public SearchingBinaryTree() {
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

        int comparisonResult;

        while (true) {
            comparisonResult = compare(currentNode, newNode);

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

        if (compare(root, new TreeNode<>(data)) == 0) {
            return true;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> newNode = new TreeNode<>(data);
        int comparisonResult;

        while (currentNode != null) {
            comparisonResult = compare(currentNode, newNode);

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

        TreeNode<E> nodeBeingDeleted = root;
        TreeNode<E> nodeBeingDeletedParent = null;
        TreeNode<E> newNode = new TreeNode<>(data);
        int comparisonResult;

        while (true) {
            comparisonResult = compare(nodeBeingDeleted, newNode);

            if (comparisonResult == 0) {
                break;
            }

            if (comparisonResult < 0) {
                if (nodeBeingDeleted.getRight() == null) {
                    return false;
                }

                nodeBeingDeletedParent = nodeBeingDeleted;
                nodeBeingDeleted = nodeBeingDeleted.getRight();
            } else {
                if (nodeBeingDeleted.getLeft() == null) {
                    return false;
                }

                nodeBeingDeletedParent = nodeBeingDeleted;
                nodeBeingDeleted = nodeBeingDeleted.getLeft();
            }
        }

        --size;

        if (nodeBeingDeleted.getLeft() == null || nodeBeingDeleted.getRight() == null) {
            comparisonResult = compare(nodeBeingDeleted, nodeBeingDeletedParent);

            if (nodeBeingDeleted.getLeft() == null && nodeBeingDeleted.getRight() == null) {
                if (nodeBeingDeletedParent == null) {
                    root = null;
                } else if (comparisonResult >= 0) {
                    nodeBeingDeletedParent.setRight(null);
                } else {
                    nodeBeingDeletedParent.setLeft(null);
                }
            } else if (nodeBeingDeleted.getRight() == null) {
                if (nodeBeingDeletedParent == null) {
                    root = nodeBeingDeleted.getLeft();
                } else if (comparisonResult < 0) {
                    nodeBeingDeletedParent.setLeft(nodeBeingDeleted.getLeft());
                } else {
                    nodeBeingDeletedParent.setRight(nodeBeingDeleted.getLeft());
                }
            } else {
                if (nodeBeingDeletedParent == null) {
                    root = nodeBeingDeleted.getRight();
                } else if (comparisonResult < 0) {
                    nodeBeingDeletedParent.setLeft(nodeBeingDeleted.getRight());
                } else {
                    nodeBeingDeletedParent.setRight(nodeBeingDeleted.getRight());
                }
            }

            return true;
        }

        TreeNode<E> parentBeingDeletedNode = nodeBeingDeletedParent;

        TreeNode<E> previousNode = nodeBeingDeleted;
        TreeNode<E> currentNode = nodeBeingDeleted.getRight();

        if (currentNode.getLeft() == null) {
            if (parentBeingDeletedNode == null) {
                currentNode.setLeft(previousNode.getLeft());
                root = nodeBeingDeleted;
            } else if (compare(currentNode, parentBeingDeletedNode) >= 0) {
                currentNode.setLeft(parentBeingDeletedNode.getRight().getLeft());
                parentBeingDeletedNode.setRight(currentNode);
            } else {
                currentNode.setLeft(parentBeingDeletedNode.getLeft().getLeft());
                parentBeingDeletedNode.setLeft(currentNode);
            }
        } else {
            while (currentNode.getLeft() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }

            previousNode.setLeft(currentNode.getRight());

            if (parentBeingDeletedNode == null) {
                currentNode.setLeft(root.getLeft());
                currentNode.setRight(root.getRight());
                root = currentNode;
            } else if (compare(currentNode, parentBeingDeletedNode) >= 0) {
                currentNode.setLeft(parentBeingDeletedNode.getRight().getLeft());
                currentNode.setRight(parentBeingDeletedNode.getRight().getRight());

                parentBeingDeletedNode.setRight(currentNode);
            } else {
                currentNode.setLeft(parentBeingDeletedNode.getLeft().getLeft());
                currentNode.setRight(parentBeingDeletedNode.getLeft().getRight());

                parentBeingDeletedNode.setLeft(currentNode);
            }
        }

        return true;
    }

    // Получение числа элементов
    public int getSize() {
        return size;
    }

    // Обход в ширину
    public void goAroundInWidth(Consumer<E> consumer) {
        if (root == null) {
            throw new NullPointerException("Пустое дерево");
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
    public void goDepthFirst(Consumer<E> consumer) {
        if (root == null) {
            throw new NullPointerException("Пустое дерево");
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
    public void goDepthFirstRecursion(Consumer<E> consumer) {
        if (root == null) {
            throw new NullPointerException("Пустое дерево");
        }

        visit(root, consumer);
    }

    public void visit(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        visit(node.getLeft(), consumer);

        visit(node.getRight(), consumer);
    }

    @Override
    public int compare(TreeNode<E> node1, TreeNode<E> node2) {
        if (comparator == null) {
            //noinspection unchecked
            Comparable<E> givenTypeNode1 = (Comparable<E>) node1.getData();

            return givenTypeNode1.compareTo(node2.getData());
        }

        return comparator.compare(node1, node2);
    }
}