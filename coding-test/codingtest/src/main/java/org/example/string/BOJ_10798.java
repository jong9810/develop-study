package org.example.string;

import java.util.Scanner;

public class BOJ_10798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = new String[5];
        for (int i = 0; i < arr.length; i++) {
            String line = sc.nextLine();
            arr[i] = line;
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i >= arr[j].length()) continue;
                System.out.print(arr[j].charAt(i));
            }
        }

        sc.close();
    }
}
