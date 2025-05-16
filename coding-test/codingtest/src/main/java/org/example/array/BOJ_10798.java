package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = 15;
        int M = 15;
        char[][] arr = new char[N][M];

        String line;
        int idx = 0;
        while ((line = br.readLine()) != null) {
            for (int j = 0; j < line.length(); j++) {
                arr[idx][j] = line.charAt(j);
            }
            idx++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[j][i] != 0) {
                    sb.append(arr[j][i]);
                }
            }
        }
        System.out.println(sb);
        br.close();
    }
}
