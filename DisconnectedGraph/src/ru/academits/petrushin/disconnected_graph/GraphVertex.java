package ru.academits.petrushin.disconnected_graph;

class GraphVertex<E> {
    private final E data;
    private GraphVertex<E>[] descendants;

    public GraphVertex(E data) {
        this.data = data;
    }

    public GraphVertex<E>[] getDescendants() {
        return descendants;
    }

    public void setDescendants(GraphVertex<E>[] descendants) {
        this.descendants = descendants;
    }

    public E getData() {
        return data;
    }
}
