package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992_2 {
    private static int[][] arr;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - 48;
            }
        }

        quadTree(0, 0, N);
        System.out.println(sb);

        br.close();
    }

    private static boolean isPossible(int x, int y, int size) {
        int first = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != first) return false;
            }
        }
        return true;
    }

    private static void quadTree(int x, int y, int size) {
        if (isPossible(x, y, size)) {
            sb.append(arr[x][y]);
            return;
        }

        sb.append("(");
        int newSize = size / 2;
        quadTree(x, y, newSize);
        quadTree(x, y + newSize, newSize);
        quadTree(x + newSize, y, newSize);
        quadTree(x + newSize, y + newSize, newSize);
        sb.append(")");
    }
}
