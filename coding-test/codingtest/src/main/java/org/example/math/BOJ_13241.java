package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13241 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        if (A == B) {
            System.out.println(A);
            return;
        }
        long lower = Math.min(A, B);
        long gcd = 1;
        for (int i = 2; i <= lower; i++) {
            if (A % i == 0 && B % i == 0) gcd = i;
        }
        System.out.println(A * B / gcd);
    }
}
