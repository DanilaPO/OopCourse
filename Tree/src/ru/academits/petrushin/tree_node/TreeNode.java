package ru.academits.petrushin.tree_node;

public class TreeNode<E> {
    private E data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(E data) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right= right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}