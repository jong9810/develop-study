package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Bottom-up
public class BOJ_1149_2 {
    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken()); // r
            cost[i][GREEN] = Integer.parseInt(st.nextToken()); // g
            cost[i][BLUE] = Integer.parseInt(st.nextToken()); // b
        }

        for (int i = 1; i < N; i++) {
            cost[i][RED] += Math.min(cost[i - 1][GREEN], cost[i - 1][BLUE]);
            cost[i][GREEN] += Math.min(cost[i - 1][RED], cost[i - 1][BLUE]);
            cost[i][BLUE] += Math.min(cost[i - 1][RED], cost[i - 1][GREEN]);
        }

        System.out.println(Math.min(cost[N - 1][0], Math.min(cost[N - 1][1], cost[N - 1][2])));
        br.close();
    }
}
