package org.example.backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_15649 {
    public static int inputLen;
    public static int outputLen;
    public static int[] arr;
    public static boolean[] visit;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        inputLen = Integer.parseInt(st.nextToken());
        outputLen = Integer.parseInt(st.nextToken());

        arr = new int[outputLen];
        visit = new boolean[inputLen];
        dfs(0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int depth) {
        if (depth == outputLen) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < inputLen; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}
