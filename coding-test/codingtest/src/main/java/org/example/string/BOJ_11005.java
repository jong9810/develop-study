package org.example.string;

import java.util.Scanner;

public class BOJ_11005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int B = sc.nextInt();

        while (N / B > 0) {
            if (N % B > 9) {
                char temp = (char) (N % B + 55);
                sb.append(temp);
            } else {
                sb.append(N % B);
            }
            N /= B;
        }
        if (N % B > 9) {
            char temp = (char) (N % B + 55);
            sb.append(temp);
        } else {
            sb.append(N % B);
        }
        System.out.println(sb.reverse());
        sc.close();
    }
}
