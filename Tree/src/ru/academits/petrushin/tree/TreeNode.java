package ru.academits.petrushin.tree;

class TreeNode<E> implements Comparable<E> {
    private E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right= right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(E data) {
        if (this.data instanceof Double) {
            return Double.compare((Double) this.data, (Double) data);
        }

        if (data instanceof Integer) {
            return Integer.compare((Integer) this.data, (Integer) data);
        }

        return -1;
    }
}