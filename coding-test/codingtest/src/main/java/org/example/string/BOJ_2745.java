package org.example.string;

import java.util.Scanner;

public class BOJ_2745 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String N = sc.next();
        int B = sc.nextInt();

        int result = 0;
        for (int i = 0; i < N.length(); i++) {
            int temp = 0;
            if (N.charAt(i) - 'A' < 0) {
                temp = N.charAt(i) - '0';
            } else {
                temp = N.charAt(i) - 'A' + 10;
            }
            result += Math.round(Math.pow(B, N.length() - i - 1)) * temp;
        }
        System.out.println(result);
        sc.close();
    }
}
