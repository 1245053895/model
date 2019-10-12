package com.xd.zt.util.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PythonReader {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("C:\\Users\\ZLJ\\Desktop\\test.py"));
        String str = null;
        int lineNumber = 0;
        while ((str = bfr.readLine()) != null) {
            lineNumber++;
            System.out.println(lineNumber + " " + str);
        }
        bfr.close();
    }
}

