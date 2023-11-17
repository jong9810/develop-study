package org.example.sort;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2751 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]);
            if (i == N - 1) break;
            sb.append("\n");
        }
        System.out.println(sb);
        sc.close();
    }
}
