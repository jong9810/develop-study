package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {
    public static int inputLen;
    public static int outputLen;
    public static int[] arr;

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        inputLen = Integer.parseInt(st.nextToken());
        outputLen = Integer.parseInt(st.nextToken());

        arr = new int[outputLen];
        dfs(1, 0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == outputLen) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= inputLen; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }
}
