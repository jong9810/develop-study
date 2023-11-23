package org.example.combinatorics;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1010 {
    /*/ 방법 1. 재귀함수 : 시간초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(combination(M, N)) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static int combination(int n, int r) {
        if (n == r || r == 0) return 1;
        return combination(n - 1, r - 1) + combination(n - 1, r);
    }
    */
    // 방법 2. 메모이제이션 사용
    public static int[][] arr = new int[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            combination(M, N);
            bw.write(String.valueOf(arr[M][N]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static int combination(int n, int r) {
        if (arr[n][r] != 0) return arr[n][r];
        if (n == r || r == 0) {
            arr[n][r] = 1;
            return 1;
        }
        arr[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
        return arr[n][r];
    }
}
