package org.example.bruteforce;

import java.util.Scanner;

public class BOJ_1436 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        /*/ 방법 1.
        int num = 666;
        int cnt = 1;
        while (cnt != N) {
            num++;
            if (String.valueOf(num).contains("666")) cnt++;
        }
        System.out.println(num);
        */
        if (N <= 1) {
            System.out.println(666);
        } else {
            func(N);
        }
        sc.close();
    }

    // 방법 2.
    private static void func(int N) {
        int prev_digit = 0;
        int cnt = 1;
        while (true) {
            prev_digit++;
            if (prev_digit % 1000 == 666) {
                for (int i = 0; i < 1000; i++) {
                    cnt++;
                    if (cnt == N) {
                        System.out.println(prev_digit * 1000 + i);
                        return;
                    }
                }
            } else if (prev_digit % 100 == 66) {
                for (int i = 0; i < 100; i++) {
                    cnt++;
                    if (cnt == N) {
                        System.out.println(prev_digit * 1000 + 600 + i);
                        return;
                    }
                }
            } else if (prev_digit % 10 == 6) {
                for (int i = 0; i < 10; i++) {
                    cnt++;
                    if (cnt == N) {
                        System.out.println(prev_digit * 1000 + 660 + i);
                        return;
                    }
                }
            } else {
                cnt++;
                if (cnt == N) {
                    System.out.println(prev_digit * 1000 + 666);
                    return;
                }
            }
        }
    }
}
