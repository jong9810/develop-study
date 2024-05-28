package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780 {

    private static int[][] arr;
    private static int[] result = new int[3]; // -1, 0, 1 종이 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, N);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);

        br.close();
    }

    private static boolean isPossible(int x, int y, int size) {
        int value = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) return false;
            }
        }
        return true;
    }

    private static void recur(int x, int y, int size) {
        if (isPossible(x, y, size)) {
            result[arr[x][y] + 1]++;
            return;
        }

        int newSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                recur(x + i * newSize, y + j * newSize, newSize);
            }
        }
    }
}
