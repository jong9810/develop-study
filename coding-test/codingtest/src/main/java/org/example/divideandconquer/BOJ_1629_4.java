package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_4 {

    private static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long result = recur(A, B);
        System.out.println(result);
        br.close();
    }

    private static long recur(long A, long exp) {
        if (exp <= 1) {
            return A % C;
        }

        long temp = recur(A, exp / 2);

        if (exp % 2 == 1) {
            return (((temp * temp) % C) * (A % C)) % C;
        }

        return (temp * temp) % C;
    }
}
