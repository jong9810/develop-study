package org.example.bruteforce;

import java.util.Scanner;

public class BOJ_2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int max5 = N / 5;
        int cnt = 0;
        while (true) {
            if (max5 < 0) {
                cnt = -1;
                break;
            }
            int remain = N - max5 * 5;
            if (remain % 3 == 0) {
                cnt = max5 + remain / 3;
                break;
            } else {
                max5--;
            }
        }
        System.out.println(cnt);
        sc.close();
    }
}
