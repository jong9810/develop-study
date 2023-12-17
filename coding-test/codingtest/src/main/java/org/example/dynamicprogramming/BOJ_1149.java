package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Top-down
// dp를 2차원 배열로 선언할 때 색의 개수는 3개밖에 되지 않기 때문에
// [N][2]로 색과 비용을 저장하는 것보다
// [N][3]으로 임의의 i에 대해 [i][0], [i][1], [i][2]에
// 각 색에 대한 최소 비용을 저장하는 것이 더 코드를 짜기 수월하다.
// 색이 아주 많거나 개수가 정해지지 않은 경우에는 [N][2]에 색을 저장하는 방법도 고려해볼만 할 것 같다.
public class BOJ_1149 {
    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public static int[][] dp;
    public static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][3];
        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        System.out.println(Math.min(calPaintCost(N - 1, 0), Math.min(calPaintCost(N - 1, 1), calPaintCost(N - 1, 2))));
        br.close();
    }

    private static int calPaintCost(int idx, int color) {
        if (dp[idx][color] == 0) {
            if (color == RED) {
                dp[idx][RED] = Math.min(calPaintCost(idx - 1, GREEN), calPaintCost(idx - 1, BLUE));
            } else if (color == GREEN) {
                dp[idx][GREEN] = Math.min(calPaintCost(idx - 1, RED), calPaintCost(idx - 1, BLUE));
            } else {
                dp[idx][BLUE] = Math.min(calPaintCost(idx - 1, RED), calPaintCost(idx - 1, GREEN));
            }
            dp[idx][color] += cost[idx][color];
        }
        return dp[idx][color];
    }
}
