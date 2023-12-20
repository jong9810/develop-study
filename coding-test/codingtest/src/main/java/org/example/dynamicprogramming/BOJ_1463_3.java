package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다른 방식으로 풀기
public class BOJ_1463_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(recur(N, 0));
        br.close();
    }

    private static int recur(int n, int cnt) {
        if (n < 2) {
            return cnt;
        }
        return Math.min(recur(n / 2, cnt + n % 2 + 1), recur(n / 3, cnt + n % 3 + 1));
    }
}
