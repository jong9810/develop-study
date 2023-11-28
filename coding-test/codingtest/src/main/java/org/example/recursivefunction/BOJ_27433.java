package org.example.recursivefunction;

import java.io.*;

public class BOJ_27433 {
    public static long[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new long[21];
        memo[0] = memo[1] = 1;

        bw.write(String.valueOf(factorial(N)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static long factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            if (memo[n] == 0) {
                return factorial(n -1) * n;
            } else {
                return memo[n];
            }
        }
    }
}
