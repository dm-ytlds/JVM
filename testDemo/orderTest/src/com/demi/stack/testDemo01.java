package com.demi.stack;

public class testDemo01 {
    public static void main(String[] args) {
        //第一类问题
        int i1 = 10;
        i1++;
        System.out.println("i1=" + i1);  // 11
        int i2 = 10;
        ++i2;
        System.out.println("i2=" + i2);  // 11
        //第二类问题
        int i3 = 10;
        int i4 = i3++;
        System.out.println("i4=" + i4); // 10
        int i5 = 10;
        int i6 = ++i5;
        System.out.println("i6=" + i6);  // 11
        //第三类问题
        int i7 = 10;
        i7 = i7++;
        System.out.println("i7=" + i7);   // 10
        int i8 = 10;
        i8 = ++i8;
        System.out.println("i8=" + i8);   // 11
        //第四类问题
        int i9 = 10;
        int i10 = i9++ + ++i9;  // i9++还是i9的值10，随后i9的值变成11，
        // 然后进行++i9，此时i9的值为12,。所以i9++ + ++i9 = 10 + 12 = 22.
        System.out.println("i10=" + i10);   // 22
    }
}
