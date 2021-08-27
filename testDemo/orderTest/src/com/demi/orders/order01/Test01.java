package com.demi.orders.order01;

public class Test01 {
    private static int num = 10;
    static {
        num = 2;
    }
    public static void main(String[] args) {
        System.out.println(num);
    }
}
