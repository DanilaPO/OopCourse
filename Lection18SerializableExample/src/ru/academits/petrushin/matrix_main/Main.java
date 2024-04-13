package ru.academits.petrushin.matrix_main;

import ru.academits.petrushin.matrix.Matrix;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(5);

        try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("halfMatrix1.bin"))) {
            out.writeObject(matrix);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("halfMatrix1.bin"))) {
            matrix = (Matrix) in.readObject();
            matrix.printMatrix();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}