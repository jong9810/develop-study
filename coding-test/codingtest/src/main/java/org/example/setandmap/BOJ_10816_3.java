package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 카운팅 배열 사용한 풀이
public class BOJ_10816_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[20000001];
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken()) + 10000000;
            arr[idx] += 1;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int find = Integer.parseInt(st.nextToken());
            sb.append(arr[find + 10000000]).append(" ");
        }
        System.out.println(sb);

        br.close();
    }
}
