package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1929 {
    /*/ 방법 1.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        for (int i = M; i <= N; i++) {
            if (isDecimal(i)) sb.append(i).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    private static boolean isDecimal(int n) {
        if (n == 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    */
    // 방법 2. 에라토스테네스의 체
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
