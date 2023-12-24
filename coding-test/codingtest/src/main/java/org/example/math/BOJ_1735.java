package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1735 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A1 = Integer.parseInt(st.nextToken()); // A : 분자
        int B1 = Integer.parseInt(st.nextToken()); // B : 분모

        st = new StringTokenizer(br.readLine());
        int A2 = Integer.parseInt(st.nextToken());
        int B2 = Integer.parseInt(st.nextToken());

        int A3 = A1 * B2 + A2 * B1; // 통분한 분수의 분자
        int B3 = B1 * B2; // 통분한 분수의 분모
        int lower = Math.min(A3, B3);

        int gcd = 1; // 최대공약수
        for (int i = lower; i > 1; i--) {
            if (A3 % i == 0 && B3 % i == 0) {
                gcd = i;
                break;
            }
        }

        System.out.println(A3 / gcd + " " + B3 / gcd);
        br.close();
    }
}
