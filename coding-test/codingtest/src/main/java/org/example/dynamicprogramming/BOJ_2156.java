package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2156 {
    public static Integer[] dp;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        System.out.println(Math.max(recur(N), recur(N - 1)));
        br.close();
    }

    private static int recur(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(recur(n - 3) + arr[n - 1], recur(n - 2)) + arr[n];
        }
        return dp[n];
    }
}
