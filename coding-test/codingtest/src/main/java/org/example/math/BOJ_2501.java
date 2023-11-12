package org.example.math;

import java.util.Scanner;

public class BOJ_2501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int cnt = 0;
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                cnt++;
                if (cnt == K) {
                    System.out.println(i);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) System.out.println(0);
        sc.close();
    }
}
