package com.demi.orders.order03;

public class MyTest01 {
    public MyTest01(int a, String b) {
        fun03(false, "a", 1);
    }
    public void fun01() {
        new MyTest01(1, "2");
    }
    public void fun02() {
        fun01();
    }

    private String fun03(boolean b, String a, int c) {
        fun02();
        return "fun03()";
    }

    public static void main(String[] args) {
        System.out.println("123");
    }

}
