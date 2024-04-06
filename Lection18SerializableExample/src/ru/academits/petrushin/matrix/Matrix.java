package ru.academits.petrushin.matrix;

import java.io.*;
import java.util.Arrays;

public class Matrix implements Serializable  {
    private Integer[][] array;

    public Matrix (int n) {
        array = new Integer[n][n];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; ++j) {
                array[i][j] = j + 1;
            }
        }
    }

    public void printMatrix () {
        for (Integer[] e : array) {
            System.out.println(Arrays.toString(e));
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        Integer[][] array1 = new Integer[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; ++j) {
                if(i < j) {
                    continue;
                }

                array1[i][j] = j + 1;
            }
        }

        out.writeObject(array1);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        array = (Integer[][]) in.readObject();
    }
}
