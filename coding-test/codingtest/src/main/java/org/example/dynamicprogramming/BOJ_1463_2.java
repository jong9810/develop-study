package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Top-down
// 재귀 함수로 하면 시간초과가 뜬다(반복문으로 해결해야 함).
public class BOJ_1463_2 {
    public static Integer [] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;

        System.out.println(recur(N));
        br.close();
    }

    private static int recur(int n) {
        if (dp[n] == null) {
            if (n % 6 == 0) {
                dp[n] = Math.min(recur(n - 1), Math.min(recur(n / 3), recur(n / 2))) + 1;
            } else if (n % 3 == 0) {
                dp[n] = Math.min(recur(n - 1), recur(n / 3)) + 1;
            } else if (n % 2 == 0) {
                dp[n] = Math.min(recur(n - 1), recur(n / 2)) + 1;
            } else {
                dp[n] = recur(n - 1) + 1;
            }
        }
        return dp[n];
    }
}
