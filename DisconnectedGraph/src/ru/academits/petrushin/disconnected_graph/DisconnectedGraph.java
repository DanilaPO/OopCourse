package ru.academits.petrushin.disconnected_graph;

import java.util.*;
import java.util.function.Consumer;

class DisconnectedGraph<E> {
    private final GraphVertex<E>[][] matrix;
    int verticesCount;

    public DisconnectedGraph(GraphVertex<E>[] vertices) {
        verticesCount = vertices.length;

        //noinspection unchecked
        matrix = new GraphVertex[vertices.length][vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            matrix[i][i] = vertices[i];
        }

        for (int i = 0; i < vertices.length; ++i) {
            GraphVertex<E> currentVertex = matrix[i][i];

            for (int j = 0; j < vertices.length; ++j) {
                if (currentVertex.getDescendants() == null) {
                    continue;
                }

                for (GraphVertex<E> e : currentVertex.getDescendants()) {
                    if (Objects.equals(matrix[j][j], e)) {
                        matrix[i][j] = e;
                    }
                }
            }
        }
    }


    // Обход в ширину
    public void goAroundInWidth(Consumer<E> consumer) {
        Queue<GraphVertex<E>> queue = new LinkedList<>();

        boolean[] visited = new boolean[verticesCount];

        int i = 0;

        while (i < verticesCount) {
            queue.add(matrix[i][i]);

            visited[i] = true;

            while (!queue.isEmpty()) {
                GraphVertex<E> currentVertex = queue.poll();

                consumer.accept(currentVertex.getData());

                if (currentVertex.getDescendants() == null) {
                    ++i;
                    continue;
                }

                for (GraphVertex<E> subsidiaryVertex : currentVertex.getDescendants()) {
                    int index = Arrays.asList(matrix[i]).indexOf(subsidiaryVertex);

                    if (visited[index]) {
                        continue;
                    }

                    queue.add(subsidiaryVertex);

                    visited[index] = true;
                }

                ++i;
            }

            System.out.println();
        }
    }

    // Обход в глубину без рекурсии
    public void goDepthFirst(Consumer<E> consumer) {
        Deque<GraphVertex<E>> stack = new LinkedList<>();

        boolean[] visited = new boolean[verticesCount];

        int i = 0;

        int index = 0;

        while (i < verticesCount) {
            stack.addFirst(matrix[i][i]);

            while (!stack.isEmpty()) {
                GraphVertex<E> currentVertex = stack.pollFirst();

                for (int j = 0; j < verticesCount; ++j) {
                    if (Objects.equals(matrix[j][j], currentVertex)) {
                        index = j;
                        break;
                    }
                }

                if (visited[index]) {
                    continue;
                }

                visited[index] = true;

                consumer.accept(currentVertex.getData());

                if (currentVertex.getDescendants() == null) {
                    ++i;
                    continue;
                }

                for (int j = currentVertex.getDescendants().length - 1; j >= 0; --j) {
                    GraphVertex<E> subsidiaryVertex = currentVertex.getDescendants()[j];

                    if (j == 0) {
                        stack.addFirst(subsidiaryVertex);

                        for (GraphVertex<E> e : subsidiaryVertex.getDescendants()) {
                            stack.addLast(e);
                        }

                        continue;
                    }

                    stack.addFirst(subsidiaryVertex);
                }

                ++i;
            }

            System.out.println();
        }
    }

    // Рекурсивный обход в глубину
    public void goDepthFirstRecursion(Consumer<E> consumer) {
        boolean[] visited = new boolean[verticesCount];

        for (int i = 0; i < verticesCount; ++i) {
            if (!visited[i]) {
                visit(matrix[i][i], visited, i, consumer);

                System.out.println();
            }
        }
    }

    public void visit(GraphVertex<E> vertex, boolean[] visited, int i, Consumer<E> consumer) {
        if (vertex == null) {
            return;
        }

        visited[i] = true;

        consumer.accept(vertex.getData());

        if (vertex.getDescendants() == null) {
            return;
        }

        for (GraphVertex<E> e : vertex.getDescendants()) {
            int index = Arrays.asList(matrix[i]).indexOf(e);

            if (index == -1 || visited[index]) {
                continue;
            }

            visited[index] = true;

            visit(e, visited, index, consumer);
        }
    }

    public void printGraph() {
        for (int string = 0; string < verticesCount; ++string) {
            for (int column = 0; column < verticesCount; ++column) {
                if (matrix[string][column] == null || string == column) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(matrix[string][column].getData() + " ");
                }
            }

            System.out.println();
        }
    }
}