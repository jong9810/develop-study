package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
    public static int N;
    public static int[] number;
    public static int[] operator = new int[4]; // 연산자 개수 배열(0: +, 1: -, 2: *, 3: /)
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(number[0], 1);

        System.out.println(max);
        System.out.println(min);
        br.close();
    }

    public static void dfs(int num, int idx) {
        if (idx == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;

                if (i == 0) {
                    dfs(num + number[idx], idx + 1);
                } else if (i == 1) {
                    dfs(num - number[idx], idx + 1);
                } else if (i == 2) {
                    dfs(num * number[idx], idx + 1);
                } else if (i == 3) {
                    dfs(num / number[idx], idx + 1);
                }
                operator[i]++;
            }
        }
    }
}