package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9461 {
    public static long[] dp = new long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(ftn(N)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static long ftn(int n) {
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = ftn(n - 3) + ftn(n - 2);
    }
}
