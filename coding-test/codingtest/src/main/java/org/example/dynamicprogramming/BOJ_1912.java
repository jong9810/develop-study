package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 반복문 사용한 코드 : 시간초과
public class BOJ_1912 {
    public static int N;
    public static int max = Integer.MIN_VALUE;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                continuitySum(i, j);
            }
        }

        System.out.println(max);
        br.close();
    }

    private static void continuitySum(int idx, int cnt) {
        int sum = 0;
        int limit = Math.min(idx + cnt, N);

        for (int i = idx; i < limit; i++) {
            sum += arr[i];
        }

        if (sum > max) max = sum;
    }
}
