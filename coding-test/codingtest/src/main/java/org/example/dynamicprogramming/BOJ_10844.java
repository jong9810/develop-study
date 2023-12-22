package org.example.dynamicprogramming;

import java.io.*;

// 시간초과 : 재귀함수 호출이 너무 많은듯(StackOverFlowError) <- 재귀함수 설계를 잘못했음.
public class BOJ_10844 {
    public static final int MAX_NUM = 1000000000;

    public static int N;
    public static int count = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 9; i++) {
            recur(i, 1);
        }

        System.out.println(count);
        br.close();
    }

    private static void recur(int n, int depth) {
        if (count >= MAX_NUM) {
            count %= MAX_NUM;
        }

        if (depth == N || n < 0) {
            return;
        }

        if (n == 0) {
            recur(n + 1, depth + 1);
        } else if (n == 9) {
            recur(n - 1, depth + 1);
        } else if (n <= 8) {
            count++;
            recur(n - 1, depth + 1);
            recur(n + 1, depth + 1);
        }
    }
}
