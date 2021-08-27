package com.demi.heap;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {

            int i = sc.nextInt();
            int j = sc.nextInt();
            double sum = i;
            for (int k = 0; k < j - 1; k++) {
                sum += Math.sqrt(i);
                double m = 0.0;
                m = Math.sqrt(i);
            }
            System.out.printf("%.2f", sum);
            if (sc.nextInt() == -1) {
                break;
            }
        }
    }

}
