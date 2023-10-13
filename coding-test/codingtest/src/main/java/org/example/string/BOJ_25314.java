package org.example.string;

import java.util.Scanner;

public class BOJ_25314 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt(); // 4의 배수
        for (int i = 0; i < n / 4; i++) {
            sb.append("long ");
        }
        sb.append("int");
        System.out.println(sb);
        sc.close();
    }
}
