package ru.academits.petrushin.tree;

// Узел дерева
class TreeNode<E> {
    private E data;
    private ru.academits.petrushin.tree.TreeNode<E> left;
    private ru.academits.petrushin.tree.TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
    }

    public ru.academits.petrushin.tree.TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(ru.academits.petrushin.tree.TreeNode<E> left) {
        this.left = left;
    }

    public ru.academits.petrushin.tree.TreeNode<E> getRight() {
        return right;
    }

    public void setRight(ru.academits.petrushin.tree.TreeNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}