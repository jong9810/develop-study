package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == B) {
                sb.append(A).append("\n");
                continue;
            }

            int gcd = 0; // 최대 공약수(Greatest Common Division)
            int lower = Math.min(A, B);
            for (int i = lower; i >= 1; i--) {
                if (A % i == 0 && B % i == 0) {
                    gcd = i;
                    break;
                }
            }

            sb.append(A * B / gcd).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
