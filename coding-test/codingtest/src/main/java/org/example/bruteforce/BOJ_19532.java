package org.example.bruteforce;

import java.util.Scanner;

public class BOJ_19532 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        for (int i = -999; i < 1000; i++) {
            for (int j = -999; j < 1000; j++) {
                int result1 = a * i + b * j - c;
                int result2 = d * i + e * j - f;
                if (result1 == 0 && result2 == 0) {
                    System.out.println(i + " " + j);
                    break;
                }
            }
        }
        int result3 = (e * c - b * f) / (a * e - b * d);
        int result4 = (a * f - d * c) / (a * e - b * d);
        System.out.println(result3 + " " + result4);
        sc.close();
    }
}
