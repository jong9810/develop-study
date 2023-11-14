package org.example.math;

import java.util.Scanner;

public class BOJ_9063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int minX = 10001;
        int maxX = -10001;
        int minY = 10001;
        int maxY = -10001;
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (minX > x) minX = x;
            if (maxX < x) maxX = x;
            if (minY > y) minY = y;
            if (maxY < y) maxY = y;
        }
        System.out.println((maxX - minX) * (maxY - minY));
        sc.close();
    }
}
