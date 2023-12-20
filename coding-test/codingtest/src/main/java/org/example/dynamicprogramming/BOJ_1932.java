package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Top-down(삼각형 꼭대기에서 밑변 방향으로)
// * 객체(Integer 등) 배열 주의점
// Integer[] 배열은 int[] 배열의 4배정도의 메모리가 소모된다고 한다.
// 자칫 재귀가 매우 깊어지거나 입력 값이 많은 경우에는 객체배열을 피하는게 좋다.
public class BOJ_1932 {
    public static int[][] arr;
    public static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new Integer[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        int max = 0;

        for (int i = 0; i < N; i++) {
            int val = recur(N - 1, i);
            max = Math.max(val, max);
            // System.out.println("idx = " + i + ", val = " + val);
        }

        System.out.println(max);
        br.close();
    }

    private static int recur(int depth, int idx) {
        if (dp[depth][idx] == null) {
            if (idx == 0) {
                dp[depth][0] = recur(depth - 1, 0);
            } else if (idx == depth) {
                dp[depth][depth] = recur(depth - 1, idx - 1);
            } else {
                dp[depth][idx] = Math.max(recur(depth - 1, idx - 1), recur(depth - 1, idx));
            }
            dp[depth][idx] += arr[depth][idx];
        }
        return dp[depth][idx];
    }
}
