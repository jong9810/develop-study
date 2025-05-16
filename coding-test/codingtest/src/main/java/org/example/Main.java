package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int [N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = 100 * N;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int diffX = Math.abs(arr[j][0] - arr[i][0]);
                int diffY = Math.abs(arr[j][1] - arr[i][1]);

                if (diffX >= 10 || diffY >= 10) continue;

                result -= (10 - diffX) * (10 - diffY);
            }
        }
        System.out.println(result);
        br.close();
    }
}
