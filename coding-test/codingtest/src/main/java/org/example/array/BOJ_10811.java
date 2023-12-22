package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 바구니 번호(1 ~ n)
        int m = Integer.parseInt(st.nextToken()); // 바구니 순서를 역순으로 만들 방법 수

        int[] basket = new int[n + 1];
        for (int i = 1; i < basket.length; i++) {
            basket[i] = i;
        }

        for (int a = 0; a < m; a++) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            // i 번째 바구니부터 j 번째 바구니의 순서를 역순으로 바꾼다.
            int len = j - i + 1;
            for (int b = 0; b < len / 2; b++) {
                int temp = basket[i + b];
                basket[i + b] = basket[j - b];
                basket[j - b] = temp;
            }
        }

        for (int i = 1; i < basket.length; i++) {
            sb.append(basket[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
