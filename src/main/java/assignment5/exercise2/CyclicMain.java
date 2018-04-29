package assignment5.exercise2;

import java.io.*;

public class CyclicMain {

    public static void main(String... args){
        CyclicObject refa = new CyclicObject("a");


        try {
            new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(refa);
        } catch (IOException e) {
            System.out.println("ALAAAARM");
            e.printStackTrace();
        }
    }
}
