package com.demi.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 */
public class Test01 {
    byte[] bytes = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        ArrayList<Test01> list = new ArrayList<>();
        while (true) {
            list.add(new Test01());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

