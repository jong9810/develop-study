package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Bottom-up(삼각형 꼭대기에서 밑변 방향으로)
public class BOJ_1932_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[][] dp = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        for (int depth = 1; depth < N; depth++) {
            for (int idx = 0; idx < depth + 1; idx++) {
                if (idx == 0) {
                    dp[depth][0] = dp[depth - 1][0];
                } else if (idx == depth) {
                    dp[depth][depth] = dp[depth - 1][idx - 1];
                } else {
                    dp[depth][idx] = Math.max(dp[depth - 1][idx], dp[depth - 1][idx - 1]);
                }
                dp[depth][idx] += arr[depth][idx];
            }
        }

        int max = dp[N - 1][0];
        for (int i = 1; i < N; i++) {
            max = Math.max(dp[N - 1][i], max);
        }

        System.out.println(max);
        br.close();
    }
}
