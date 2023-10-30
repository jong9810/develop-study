package org.example.math;

import java.util.Scanner;

public class BOJ_5086 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0 && b == 0) break;
            if (b % a == 0) sb.append("factor");
            else if(a % b == 0) sb.append("multiple");
            else sb.append("neither");
            sb.append("\n");
        }
        System.out.println(sb);
        sc.close();
    }
}
