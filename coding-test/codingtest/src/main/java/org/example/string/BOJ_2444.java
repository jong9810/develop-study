package org.example.string;

import java.util.Scanner;

public class BOJ_2444 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            oneLine(n, i);
        }
        middleLine(n);
        for (int i = n - 2; i > -1; i--) {
            oneLine(n, i);
        }

        sc.close();
    }

    private static void middleLine(int n) {
        for (int i = 0; i < 2 * n - 1; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private static void oneLine(int n, int i) {
        for (int j = 0; j < n - i - 1; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < 2 * i + 1; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
