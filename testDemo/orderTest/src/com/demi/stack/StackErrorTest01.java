package com.demi.stack;

/**
 * 演示StackOverflowError。递归方法中容易出现。
 */
public class StackErrorTest01 {

    // 测试栈内存大小
    static int count = 1;
    // 最终结果：11404->1024KB

    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
