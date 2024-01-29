package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17103 {
    public static boolean[] prime = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        getPrime();

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 0;
            for (int i = 2; i <= N / 2; i++) {
                if (!prime[i] && !prime[N - i]) cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void getPrime() {
        prime[0] = prime[1] = true;
        for (int i = 2; i <= Math.sqrt(prime.length - 1); i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
