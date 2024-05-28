package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간초과
public class BOJ_1629_3 {

    private static int A;
    private static int B;
    private static int C;

    private static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        recur(B);
        System.out.println(result);
        br.close();
    }

    private static void recur(int exp) {
        if (exp <= 1) {
            result = (result * (A % C)) % C;
            return;
        }

        if (exp % 2 == 0) {
            recur(exp / 2);
            recur(exp / 2);
        } else {
            recur(exp / 2);
            recur(exp / 2);
            recur(1);
        }
    }
}
