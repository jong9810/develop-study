package org.example.search;

import java.util.Scanner;

public class BOJ_2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = 0; // 검은 영역 넓이
        boolean[][] arr = new boolean[101][101]; // 도화지
        int N = sc.nextInt(); // 색종이 개수
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt(); // 색종이 왼쪽 좌표
            int y = sc.nextInt(); // 색종이 아래쪽 좌표

            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    if (!arr[j][k]) {
                        arr[j][k] = true;
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
        sc.close();
    }
}