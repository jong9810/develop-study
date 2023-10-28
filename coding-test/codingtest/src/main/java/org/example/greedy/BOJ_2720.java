package org.example.greedy;

import java.util.Scanner;

public class BOJ_2720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] coin = {25, 10, 5, 1};
        for (int t = 0; t < T; t++) {
            int C = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < coin.length; i++) {
                int cnt = C / coin[i];
                C %= coin[i];
                sb.append(cnt);
                if (i != 3) sb.append(" ");
            }
            System.out.println(sb);
        }
        sc.close();
    }
}
