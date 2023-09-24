package ru.academits.petrushin.example1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class Example1 {
    public static void main(String[] args) throws IOException {
        try (BufferedInputStream streamRead = new BufferedInputStream(new FileInputStream("fileInputExm1.txt"));
             BufferedOutputStream streamWrite = new BufferedOutputStream(new FileOutputStream("fileOutputExm1.txt"))) {
            int read;
            int off = 0;
            byte[] res = new byte[100];

            while ((read = streamRead.read(res, off, res.length - off)) != -1) {
                off += read;
            }

            streamWrite.write(res);
        }
    }
}