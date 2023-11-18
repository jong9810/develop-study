package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == B) {
                System.out.println(A);
                continue;
            }
            int gcd = 1; // 최대 공약수(Greatest Common Division)
            int lower = Math.min(A, B);
            for (int i = 2; i <= lower; i++) {
                if (A % i == 0 && B % i == 0) gcd = i;
            }
            System.out.println(A * B / gcd);
        }
    }
}
