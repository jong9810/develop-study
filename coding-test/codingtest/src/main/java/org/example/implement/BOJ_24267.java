package org.example.implement;

import java.util.Scanner;

public class BOJ_24267 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt = N * (N - 1) * (N - 2) / 6;
        System.out.println(cnt);
        System.out.println(3);
        sc.close();
    }
}
