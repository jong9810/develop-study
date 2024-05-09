package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630 {

    private static int N;
    private static int[][] arr;
    private static int[] cnt = new int[2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, N);

        System.out.println(cnt[0]);
        System.out.println(cnt[1]);
    }

    private static boolean check(int iStart, int jStart, int n) {
        int first = arr[iStart][jStart];
        for (int i = iStart; i < iStart + n; i++) {
            for (int j = jStart; j < jStart + n; j++) {
                if (first != arr[i][j]) return false;
            }
        }
        return true;
    }

    private static void recur(int iStart, int jStart, int n) {
        if (n <= 0) return;

        boolean isSameColor = check(iStart, jStart, n);

        // 만약 4등분한 영역의 색이 같을 경우
        if (isSameColor) {
            int color = arr[iStart][jStart];
            cnt[color]++;
            return;
        }

        // 만약 4등분한 영역의 색이 다를 경우
        recur(iStart, jStart, n / 2);
        recur(iStart + n / 2, jStart, n / 2);
        recur(iStart, jStart + n / 2, n / 2);
        recur(iStart + n / 2, jStart + n / 2, n / 2);
    }

}
