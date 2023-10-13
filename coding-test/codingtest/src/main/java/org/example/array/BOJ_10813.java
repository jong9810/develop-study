package org.example.array;

import java.util.Scanner;

public class BOJ_10813 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 바구니 번호(1 ~ n)
        int m = sc.nextInt(); // 공을 교환하는 횟수

        // 바구니 초기화
        int[] basket = new int[n + 1];
        for (int i = 1; i < basket.length; i++) {
            basket[i] = i;
        }

        for (int b = 0; b < m; b++) {
            // i 번째 바구니와 j 번째 바구니의 공을 교환한다.
            int i = sc.nextInt();
            int j = sc.nextInt();

            int temp = basket[i];
            basket[i] = basket[j];
            basket[j] = temp;
        }

        for (int i = 1; i < basket.length; i++) {
            System.out.print(basket[i] + " ");
        }
        sc.close();
    }
}
