package org.example.dynamicprogramming;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_9184 {
    public static int[][][] memo = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line.equals("-1 -1 -1")) break;

            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a, b, c)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static int w(int a, int b, int c) {
        if (inRange(a, b, c) && memo[a][b][c] != 0) {
            return memo[a][b][c];
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return memo[20][20][20] = w(20, 20, 20);
        }

        if (a < b && b < c) {
            return memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }

        return memo[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    public static boolean inRange(int a, int b, int c) {
        return a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20;
    }
}
