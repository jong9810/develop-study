package org.example.array;

import java.util.Scanner;

public class BOJ_10810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 바구니, 공 번호(1 ~ n)
        int m = sc.nextInt(); // 공을 바구니에 넣는 방법 수

        int[] ball = new int[n + 1];
        for (int b = 0; b < m; b++) {
            // i번 바구니부터 j번 바구니까지 k번 공을 집어넣음
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();

            for (int l = i; l <= j; l++) {
                ball[l] = k;
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(ball[i] + " ");
        }

        sc.close();
    }
}
