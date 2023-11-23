package org.example.combinatorics;

import java.io.*;

public class BOJ_10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        if (N == 0 || N == 1) {
            System.out.println(1);
            return;
        }
        int[] arr = new int[N + 1];
        arr[0] = arr[1] = 1;
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] * i;
        }
        bw.write(String.valueOf(arr[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
