package org.example.bruteforce;

import java.util.Scanner;

public class BOJ_2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int tempN = N;
        int digit = 0;
        while (tempN / 10 >= 0) {
            tempN /= 10;
            digit++;
            if (tempN == 0) break;
        }
        int goe = N - 9 * digit;
        int loe = N - digit;
        int result = 0;
        for (int i = goe; i <= loe; i++) {
            int tempI = i;
            int sum = tempI;
            while (tempI / 10 >= 0) {
                sum += tempI % 10;
                tempI /= 10;
                if (tempI == 0) break;
            }
            if (sum == N) {
                result = i;
                break;
            }
        }
        System.out.println(result);
        sc.close();
    }
}
