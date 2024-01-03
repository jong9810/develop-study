package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int base = 2;
        for (int i = 0; i < N; i++) {
            int add = (int) Math.pow(2, i);
            base += add;
        }

        System.out.println(base * base);
        br.close();
    }
}
