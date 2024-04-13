package ru.academits.petrushin.matrix;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix implements Serializable  {
    private int[][] array;

    public Matrix (int n) {
        array = new int[n][n];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; ++j) {
                array[i][j] = j + 1;
            }
        }
    }

    public void printMatrix () {
        for (int[] e : array) {
            System.out.println(Arrays.toString(e));
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        int[][] array1 = new int[array.length][];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            queue.add(new int[i + 1]);

            for (int j = 0; j < array.length; ++j) {
                if(i < j) {
                    continue;
                }
                queue.peek()[j] = j + 1;
            }

            array1[i] = queue.poll();
        }

        out.writeObject(array1);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        array = (int[][]) in.readObject();
    }
}