package com.demi.gc;

import java.util.ArrayList;

public class GCTest {

    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        while(true){
            byte[] arr = new byte[100];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
