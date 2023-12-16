package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_24416 {
    public static int[] arr;
    public static int cnt1;
    public static int cnt2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        arr[1] = arr[2] = 1;

        fibonacci1(N);
        fibonacci2(N);

        System.out.println(cnt1 + " " + cnt2);

        br.close();
    }

    public static int fibonacci1(int n) {
        if (n == 1 || n == 2) {
            cnt1++;
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    public static void fibonacci2(int n) {
        for (int i = 3; i <= n; i++) {
            cnt2++;
            arr[i] = arr[i - 1] + arr[i - 2];
        }
    }
}
