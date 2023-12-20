package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Bottom-up
// 6으로 나누어 떨어지는 경우를 생각하지 못해서 틀렸음!
public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];
        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            if (i % 6 == 0) {
                dp[i] = Math.min(dp[i - 1], Math.min(dp[i / 3], dp[i / 2])) + 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i - 1], dp[i / 3]) + 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i - 1], dp[i / 2]) + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}
