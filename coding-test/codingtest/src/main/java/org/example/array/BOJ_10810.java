package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_10810 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 바구니, 공 번호(1 ~ n)
        int m = Integer.parseInt(st.nextToken()); // 공을 바구니에 넣는 방법 수

        int[] basket = new int[n + 1]; // 인덱스: 바구니 번호, 값: 해당 바구니에 들어있는 공의 숫자
        for (int b = 0; b < m; b++) {
            st = new StringTokenizer(br.readLine());

            // i번 바구니부터 j번 바구니까지 k번 공을 집어넣음
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int idx = i; idx <= j; idx++) {
                basket[idx] = k;
            }   
        }

        for (int i = 1; i <= n; i++) {
            sb.append(basket[i]).append( " ");
        }

        System.out.println(sb);
        br.close();
    }
}
