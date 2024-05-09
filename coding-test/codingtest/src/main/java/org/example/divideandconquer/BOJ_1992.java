package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 결과 : 틀렸습니다.
public class BOJ_1992 {
    private static int N;
    private static int[][] arr;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - 48;
            }
        }

        recur(0, 0, N);
        System.out.println(sb);

        br.close();
    }

    private static int check(int iStart, int jStart, int n) {
        int first = arr[iStart][jStart];
        for (int i = iStart; i < iStart + n; i++) {
            for (int j = jStart; j < jStart + n; j++) {
                if (arr[i][j] != first) return -1;
            }
        }
        return first;
    }

    private static void recur(int iStart, int jStart, int n) {
        if (n < 1) {
            return;
        }

        if (n == N) {
            int firstCheck = check(iStart, jStart, n);
            if (firstCheck != -1) {
                sb.append("(").append(firstCheck).append(")");
            } else {
                recur(iStart, jStart, n / 2);
            }
            return;
        }

        int[] quarterCheck = new int[4];
        quarterCheck[0] = check(iStart, jStart, n);
        quarterCheck[1] = check(iStart, jStart + n, n);
        quarterCheck[2] = check(iStart + n, jStart, n);
        quarterCheck[3] = check(iStart + n, jStart + n, n);

        sb.append("(");
        for (int i = 0; i < 4; i++) {
            if (quarterCheck[i] != -1) {
                sb.append(quarterCheck[i]);
            } else {
                if (i == 0) {
                    recur(iStart, jStart, n / 2);
                } else if (i == 1) {
                    recur(iStart, jStart + n, n / 2);
                } else if (i == 2) {
                    recur(iStart + n, jStart, n / 2);
                } else if (i == 3) {
                    recur(iStart + n, jStart + n, n / 2);
                }
            }
        }
        sb.append(")");
    }
}
