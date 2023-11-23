package org.example.combinatorics;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_11050 {
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int max = Math.max(N, Math.max(K, N - K));
        factorial(max);
        bw.write(String.valueOf(arr[N] / (arr[K] * arr[N - K])));
        bw.flush();
        bw.close();
        br.close();
    }
    private static void factorial(int N) {
        if (N == 0 || N == 1) {
            arr = new int[]{1, 1};
            return;
        }
        arr = new int[N + 1];
        arr[0] = arr[1] = 1;
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] * i;
        }
    }
}
