package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579 {
    public static int[] arr;
    public static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[301];
        dp = new Integer[301];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        System.out.println(upStairs(N));
        br.close();
    }

    private static int upStairs(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(upStairs(n - 3) + arr[n - 1], upStairs(n - 2)) + arr[n];
        }
        return dp[n];
    }
}
