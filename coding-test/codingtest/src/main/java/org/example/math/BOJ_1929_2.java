package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 에라토스테네스의 체
public class BOJ_1929_2 {
    public static boolean[] notPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        notPrime = new boolean[N + 1];
        getPrime();

        for (int i = M; i <= N; i++) {
            if (!notPrime[i]) sb.append(i).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void getPrime() {
        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(notPrime.length); i++) {
            if(notPrime[i]) continue;
            for (int j = i * i; j < notPrime.length; j += i) {
                notPrime[j] = true;
            }
        }
    }
}
