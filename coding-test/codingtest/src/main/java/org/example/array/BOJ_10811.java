package org.example.array;

import java.util.Scanner;

public class BOJ_10811 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 바구니 번호(1 ~ n)
        int m = sc.nextInt(); // 바구니 순서를 역순으로 만들 방법 수

        int[] basket = new int[n + 1];
        for (int i = 1; i < basket.length; i++) {
            basket[i] = i;
        }

        for (int a = 0; a < m; a++) {
            // i 번째 바구니부터 j 번째 바구니의 순서를 역순으로 바꾼다.
            int i = sc.nextInt();
            int j = sc.nextInt();

            int len = j - i + 1;
            for (int b = 0; b < len / 2; b++) {
                int temp = basket[i + b];
                basket[i + b] = basket[j - b];
                basket[j - b] = temp;
            }
        }
        for (int i = 1; i < basket.length; i++) {
            System.out.print(basket[i] + " ");
        }

        sc.close();
    }
}
