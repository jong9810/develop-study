package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

// 유클리드 호제법 :
public class BOJ_2485_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        int dist;
        int gcd = 0;
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
            if (i > 0) {
                dist = input[i] - input[i - 1];
                gcd = gcd(dist, gcd);
            }
        }

        System.out.println((input[N - 1] - input[0]) / gcd - N + 1);
        br.close();
    }

    private static int gcd(int num1, int num2) {
        while (num2 > 0) {
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }
        return num1;
    }
}
