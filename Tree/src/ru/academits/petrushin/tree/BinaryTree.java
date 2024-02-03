package ru.academits.petrushin.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// TODO: 22. Обходы - нужно сделать, чтобы они могли выполнять любой
public class BinaryTree<E> {
    private TreeNode<E> root;

    int size;

    // вставка узла
    public void add(E data) {
        ++size;

        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (currentNode.compareTo(data) <= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));

                    return;
                }

                currentNode = currentNode.getRight();
            }

            if (currentNode.compareTo(data) > 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));

                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    // поиск узла
    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        if (root.compareTo(data) == 0) {
            return true;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (currentNode.compareTo(data) == 0) {
                return true;
            }

            if (currentNode.compareTo(data) <= 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }

            if (currentNode.compareTo(data) > 0) {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    // удаление первого вхождения
    public boolean removeFirst(E data) {
        if (root == null || data == null) {
            return false;
        }

        --size;

        TreeNode<E> currentNode = root;
        TreeNode<E> previousNode = null;

        while (currentNode.compareTo(data) != 0) {
            if (currentNode.compareTo(data) <= 0) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            }

            if (currentNode.compareTo(data) <= 0) {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }

            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                if (previousNode == null) {
                    root = null;
                } else if (currentNode.compareTo(previousNode.getData()) >= 0) {
                    previousNode.setRight(null);
                } else {
                    previousNode.setLeft(null);
                }
            } else if (currentNode.getRight() == null) {
                if (previousNode == null) {
                    root = currentNode.getLeft();
                } else if (currentNode.compareTo(previousNode.getData()) < 0) {
                    previousNode.setLeft(currentNode.getLeft());
                } else {
                    previousNode.setRight(currentNode.getLeft());
                }
            } else {
                if (previousNode == null) {
                    root = currentNode.getRight();
                } else if (currentNode.compareTo(previousNode.getData()) < 0) {
                    previousNode.setLeft(currentNode.getRight());
                } else {
                    previousNode.setRight(currentNode.getRight());
                }
            }

            return true;
        }

        TreeNode<E> parentBeingDeletedNode = previousNode;

        previousNode = currentNode;
        currentNode = currentNode.getRight();

        if (currentNode.getLeft() == null) {
            if (parentBeingDeletedNode == null) {
                currentNode.setLeft(previousNode.getLeft());
                root = currentNode;
            } else if (currentNode.compareTo(parentBeingDeletedNode.getData()) >= 0) {
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

            if (currentNode.getRight() == null) {
                previousNode.setLeft(null);
            } else {
                previousNode.setLeft(currentNode.getRight());
            }

            if (parentBeingDeletedNode == null) {
                currentNode.setLeft(root.getLeft());
                currentNode.setRight(root.getRight());
                root = currentNode;
            } else if (currentNode.compareTo(parentBeingDeletedNode.getData()) >= 0) {
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

    // получение числа элементов
    public int getSize() {
        return size;
    }

    // обход в ширину
    public void toBreadthFirst() {
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
    public void toDepthFirst() {
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
    public void toDepthFirstRecursion() {
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