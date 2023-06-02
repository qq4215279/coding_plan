package com.mumu.user;

import java.io.*;
import java.util.ArrayList;

public class ReadMenberId {

    public ArrayList<String> abc() throws IOException {

        BufferedReader fr = new BufferedReader(new FileReader("H:\\MajorData\\IDEAWorkspace\\temData\\abc.txt"));

        String line;
        ArrayList<String> list = new ArrayList<>();
        while ((line = fr.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(new ReadMenberId().abc());
    }
}
