package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://st-lab.tistory.com/122 블로그 참고
// team1, team2 배열을 만들지 않고 재귀함수를 N / 2번 실행했을 때 visit 배열에 true인 인덱스와 false인 인덱스로 팀을 나누었다.
// 만약 두 팀의 점수 차가 0이면 최소값이므로 System.exit(0)으로 실행을 끝냈다(최적화).
public class BOJ_14889_2 {
    public static int N;
    public static int[][] map;
    public static boolean[] visit;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);

        System.out.println(min);
        br.close();
    }

    public static void combi(int idx, int count) {
        if (count == N / 2) {
            diff();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combi(i + 1, count + 1);
                visit[i] = false;
            }
        }
    }

    public static void diff() {
        int team1 = 0;
        int team2 = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] && visit[j]) {
                    team1 += map[i][j];
                    team1 += map[j][i];
                } else if (!visit[i] && !visit[j]){
                    team2 += map[i][j];
                    team2 += map[j][i];
                }
            }
        }

        int val = Math.abs(team1 - team2);
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }
        min = Math.min(min, val);
    }
}
