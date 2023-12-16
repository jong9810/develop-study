package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1904 : 재귀 함수 사용하는 다른 코드.
public class BOJ_1904_2 {
    public static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = -1;
        }


        System.out.println(ftn(N));
        br.close();
    }

    private static int ftn(int idx) {
        if (dp[idx] == -1) {
            dp[idx] = (ftn(idx - 1) + ftn(idx - 2)) % 15746;
        }
        return dp[idx];
    }
}
