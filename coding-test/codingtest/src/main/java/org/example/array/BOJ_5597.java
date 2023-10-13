package org.example.array;

import java.util.Scanner;

public class BOJ_5597 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 30; // 학생 수
        int m = 28; // 과제를 제출한 학생 수
        int[] student = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int submit = sc.nextInt();
            student[submit] = 1;
        }

        for (int i = 1; i < student.length; i++) {
            if (student[i] == 0) {
                System.out.println(i);
            }
        }

        sc.close();
    }
}
