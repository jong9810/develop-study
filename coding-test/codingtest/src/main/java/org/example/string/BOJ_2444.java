package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2444 {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            oneLine(n, i);
        }
        middleLine(n);
        for (int i = n - 2; i > -1; i--) {
            oneLine(n, i);
        }

        System.out.println(sb);
        br.close();
    }

    private static void middleLine(int n) {
        sb.append("*".repeat(2 * n - 1));
        sb.append("\n");
    }

    private static void oneLine(int n, int i) {
        sb.append(" ".repeat(n - i - 1));
        sb.append("*".repeat(2 * i + 1));
        sb.append("\n");;
    }
}
