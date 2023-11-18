package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1735 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A1 = Integer.parseInt(st.nextToken());
        int B1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int A2 = Integer.parseInt(st.nextToken());
        int B2 = Integer.parseInt(st.nextToken());
        int numerator = A1 * B2 + A2 * B1; // 분자
        int denominator = B1 * B2; // 분모
        int lower = Math.min(numerator, denominator);
        int gcd = 1; // 최대공약수
        for (int i = 2; i <= lower; i++) {
            if (numerator % i == 0 && denominator % i == 0) gcd = i;
        }
        System.out.println(numerator / gcd + " " + denominator / gcd);
    }
}
