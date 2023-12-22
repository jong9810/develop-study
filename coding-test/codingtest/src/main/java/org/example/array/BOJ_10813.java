package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10813 {
    public static void main(String[] args)throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 바구니 번호(1 ~ n)
        int m = Integer.parseInt(st.nextToken()); // 공을 교환하는 횟수

        // 바구니 초기화
        int[] basket = new int[n + 1];
        for (int i = 1; i < basket.length; i++) {
            basket[i] = i;
        }

        for (int b = 0; b < m; b++) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            // i 번째 바구니와 j 번째 바구니의 공을 교환한다.
            int temp = basket[i];
            basket[i] = basket[j];
            basket[j] = temp;
        }

        for (int i = 1; i < basket.length; i++) {
            sb.append(basket[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
