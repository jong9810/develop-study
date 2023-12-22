package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5597 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = 30; // 학생 수
        int m = 28; // 과제를 제출한 학생 수
        int[] student = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int submit = Integer.parseInt(br.readLine());
            student[submit] = 1;
        }

        for (int i = 1; i < student.length; i++) {
            if (student[i] == 0) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}
