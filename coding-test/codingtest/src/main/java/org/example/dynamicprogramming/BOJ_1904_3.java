package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1904 : 반복문을 사용하는 코드(재귀 함수를 사용하는 것보다 성능이 우수함).
public class BOJ_1904_3 {
    public static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[N]);
        br.close();
    }
}
