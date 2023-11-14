package org.example.implement;

import java.util.Scanner;

public class BOJ_24313 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a1 = sc.nextInt();
        int a0 = sc.nextInt();
        int c = sc.nextInt();
        int n0 = sc.nextInt();
        int result = 0;
        if (a1 <= c && (n0 * a1 + a0) <= (c * n0)) {
            result = 1;
        }
        System.out.println(result);
    }
}
