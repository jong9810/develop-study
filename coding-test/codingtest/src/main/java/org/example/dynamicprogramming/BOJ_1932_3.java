package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Top-down(삼각형 밑변에서 꼭대기 방향으로)
public class BOJ_1932_3 {
    public static int N;
    public static int[][] arr;
    public static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new Integer[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = arr[N - 1][i];
        }

        System.out.println(recur(0, 0));
        br.close();
    }

    private static int recur(int depth, int idx) {
        if (dp[depth][idx] == null) {
            dp[depth][idx] = Math.max(recur(depth + 1, idx), recur(depth + 1, idx + 1)) + arr[depth][idx];
        }
        return dp[depth][idx];
    }
}
