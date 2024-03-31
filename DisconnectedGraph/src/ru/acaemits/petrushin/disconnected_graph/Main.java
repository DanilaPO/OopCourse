package ru.acaemits.petrushin.disconnected_graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        GraphVertex<Integer> vertex1 = new GraphVertex<Integer>(1);
        GraphVertex<Integer> vertex2 = new GraphVertex<Integer>(2);
        GraphVertex<Integer> vertex3 = new GraphVertex<Integer>(3);
        GraphVertex<Integer> vertex4 = new GraphVertex<Integer>(4);
        GraphVertex<Integer> vertex5 = new GraphVertex<Integer>(5);
        GraphVertex<Integer> vertex6 = new GraphVertex<Integer>(6);
        GraphVertex<Integer> vertex7 = new GraphVertex<Integer>(7);

        GraphVertex<Integer> vertex8 = new GraphVertex<Integer>(8);
        GraphVertex<Integer> vertex9 = new GraphVertex<Integer>(9);

        GraphVertex<Integer> vertex10 = new GraphVertex<Integer>(10);

        vertex1.setDescendants(vertex2);
        vertex2.setDescendants(vertex1, vertex3, vertex4, vertex5, vertex6);
        vertex3.setDescendants(vertex2, vertex7);
        vertex4.setDescendants(vertex2);
        vertex5.setDescendants(vertex2, vertex6);
        vertex6.setDescendants(vertex2, vertex5, vertex7);
        vertex7.setDescendants(vertex3, vertex6);

        vertex8.setDescendants(vertex9);
        vertex9.setDescendants(vertex8);

        DisconnectedGraph<Integer> graph = new DisconnectedGraph<>(vertex1, vertex2, vertex3, vertex4, vertex5, vertex6, vertex7, vertex8, vertex9, vertex10);

;

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        System.out.println("Печать графа:");
        graph.printGraph();
        System.out.println();

        System.out.println("Обход графа в ширину:");
        graph.goAroundInWidth(consumer);
        System.out.println();

        System.out.println("Обход графа в глубину без рекурссии:");
        graph.goDepthFirst(consumer);
        System.out.println();

        System.out.println("Рекурсивный Обход графа в глубину:");
        graph.goDepthFirstRecursion(consumer);
    }
}