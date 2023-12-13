package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방법1. 시간초과
public class BOJ_14889 {
    public static int N;
    public static int[][] status;
    public static int[] team1;
    public static int[] team2;
    public static boolean[] visit;
    public static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        status = new int[N][N];
        team1 = new int[N / 2];
        team2 = new int[N / 2];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(result);
        br.close();
    }

    public static void dfs(int depth) {
        if (depth == N) {
            int score1 = addScore(team1);
            int score2 = addScore(team2);
            result = Math.min(result, Math.abs(score1 - score2));

//            System.out.println("====================================");
//            System.out.println("score1 = " + score1);
//            System.out.println("score2 = " + score2);
//            System.out.println("result = " + result);
//            System.out.println("====================================");

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                if (depth < N / 2) {
                    team1[depth] = i;
                } else {
                    team2[depth - N / 2] = i;
                }
                visit[i] = true;

//                System.out.println("--------------------------------------");
//                System.out.println("i = " + i);
//                System.out.println("depth = " + depth);
//                System.out.println("visit = " + Arrays.toString(visit));
//                System.out.println("team1 = " + Arrays.toString(team1));
//                System.out.println("team2 = " + Arrays.toString(team2));
//                System.out.println("--------------------------------------");

                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }

    public static int addScore(int[] team) {
        int totalScore = 0;
        for (int i = 0; i < team.length - 1; i++) {
            for (int j = i + 1; j < team.length; j++) {
                totalScore += status[team[i]][team[j]];
                totalScore += status[team[j]][team[i]];
            }
        }
        return totalScore;
    }
}
