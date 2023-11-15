package org.example.bruteforce;

import java.util.Scanner;

public class BOJ_1018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] line1 = {87, 66, 87, 66, 87, 66, 87, 66};
        int[] line2 = {66, 87, 66, 87, 66, 87, 66, 87};
        int[][] sample1 = new int[8][8];
        int[][] sample2 = new int[8][8];
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                sample1[i] = line1;
                sample2[i] = line2;
            } else {
                sample1[i] = line2;
                sample2[i] = line1;
            }
        }
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j);
            }
        }
        int row = N - 8 + 1;
        int col = M - 8 + 1;
        int cnt = 64;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int tempCnt1 = 0;
                int tempCnt2 = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[r + i][c + j] != sample1[i][j]) tempCnt1++;
                        if (board[r + i][c + j] != sample2[i][j]) tempCnt2++;
                    }
                }
                int tempCntMin = Math.min(tempCnt1, tempCnt2);
                if (tempCntMin < cnt) {
                    cnt = tempCntMin;
                }
            }
        }
        System.out.println(cnt);
        sc.close();
    }
}
