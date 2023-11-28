package org.example.recursivefunction;

import java.io.*;

public class BOJ_10870 {
    public static int[] memo = new int[21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo[0] = 0;
        memo[1] = 1;

        bw.write(String.valueOf(fibonacci(N)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return memo[n];
        } else {
            if (memo[n] == 0) {
                return fibonacci(n - 2) + fibonacci(n - 1);
            } else {
                return memo[n];
            }
        }
    }
}
