package org.example.math;

import java.util.Scanner;

public class BOJ_2903 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int base = 2;
        for (int i = 0; i < N; i++) {
            int add = (int) Math.pow(2, i);
            base += add;
        }
        System.out.println(base * base);
        sc.close();
    }
}
