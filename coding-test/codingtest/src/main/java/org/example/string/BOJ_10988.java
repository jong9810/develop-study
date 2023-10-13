package org.example.string;

import java.util.Scanner;

public class BOJ_10988 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        int len = word.length();
        int result = 1;
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - 1 - i)) {
                result = 0;
                break;
            }
        }
        System.out.println(result);
        sc.close();
    }
}
