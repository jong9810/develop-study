package org.example.array;

import java.util.Scanner;

public class BOJ_10807 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 0;
        int[] arr = new int[201];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            arr[num + 100]++;
        }
        int v = sc.nextInt();
        System.out.println(arr[v + 100]);

        sc.close();
    }
}
