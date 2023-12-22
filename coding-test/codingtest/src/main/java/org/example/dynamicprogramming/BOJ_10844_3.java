package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Top-down
public class BOJ_10844_3 {
    public static final int MAX_NUM = 1000000000;

    public static Integer[][] numCnt = new Integer[101][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        numCnt[1] = new Integer[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 0; i < 10; i++) {
            recur(N, i);
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += numCnt[N][i];
        }
        System.out.println(result % MAX_NUM);

        br.close();
    }

    private static int recur(int n, int idx) {
        if (numCnt[n][idx] == null) {
            if (idx == 0) {
                numCnt[n][idx] = recur(n - 1, idx + 1);
            } else if (idx == 9) {
                numCnt[n][idx] = recur(n - 1, idx - 1);
            } else {
                numCnt[n][idx] = (recur(n - 1, idx - 1) + recur(n - 1, idx + 1));
            }
        }
        return numCnt[n][idx] % MAX_NUM;
    }
}
