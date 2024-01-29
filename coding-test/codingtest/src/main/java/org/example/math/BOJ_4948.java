package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4948 {

    public static boolean[] notPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            int M = 2 * N;
            notPrime = new boolean[M + 1];
            notPrime[0] = notPrime[1] = true;
            getPrime();

            int cnt = 0;
            for (int i = N + 1; i <= M; i++) {
                if (!notPrime[i]) cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
    private static void getPrime() {
        for (int i = 2; i <= Math.sqrt(notPrime.length - 1); i++) {
            if (notPrime[i]) continue;
            for (int j = i * i; j < notPrime.length; j += i) {
                notPrime[j] = true;
            }
        }
    }
}
