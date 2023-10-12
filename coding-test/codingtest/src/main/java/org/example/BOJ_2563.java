package org.example;

import java.util.Scanner;

public class BOJ_2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] pos = new int[N][4];
        for (int i = 0; i < N; i++) {
            pos[i][0] = sc.nextInt(); // 왼쪽 끝
            pos[i][1] = sc.nextInt(); // 아래쪽 끝
            pos[i][2] = pos[i][0] + 10; // 오른쪽 끝
            pos[i][3] = pos[i][1] + 10; // 위쪽 끝
        }

        int result = N * 100;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                boolean isILeft = pos[i][0] < pos[j][0];
                boolean isIRight = pos[i][0] > pos[j][0];
                if (isILeft) {
                    if (pos[i][2] < pos[j][0]) continue;
                    if (pos[i][1] < pos[j][1]) {
                        if (pos[i][3] > pos[j][1])
                            result -= Math.abs(pos[i][2] - pos[j][0]) * Math.abs(pos[i][3] - pos[j][1]);
                    } else if (pos[i][1] > pos[j][1]) {
                        if (pos[i][1] < pos[j][3])
                            result -= Math.abs(pos[i][2] - pos[j][0]) * Math.abs(pos[i][1] - pos[j][3]);
                    }
                } else if (isIRight) {
                    if (pos[i][0] > pos[j][2]) continue;
                    if (pos[i][1] < pos[j][1]) {
                        if (pos[i][3] > pos[j][1])
                            result -= Math.abs(pos[i][0] - pos[j][2]) * Math.abs(pos[i][3] - pos[j][1]);
                    } else if (pos[i][1] > pos[j][1]) {
                        if (pos[i][1] < pos[j][3])
                            result -= Math.abs(pos[i][0] - pos[j][2]) * Math.abs(pos[i][1] - pos[j][3]);
                    }
                }

            }
        }

        System.out.println(result);

        sc.close();
    }
}