package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1629_3 {

    private static int A;
    private static int B;
    private static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        recur();
        System.out.println();
        br.close();
    }

    private static void recur(int exp, int remain) {
        int temp = A;
        if (exp <= 1) {
            if (remain == 0) {
                temp = (temp % C) * (temp % C) % C;
            } else {
                temp = ((temp % C) * (temp % C) % C) % C;
            }
        }
    }
}
