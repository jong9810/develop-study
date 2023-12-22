package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Bottom-up
public class BOJ_10844_2 {
    public static final int MAX_NUM = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] numCnt = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 2; i <= N; i++) {
            int[] temp = new int[10];
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    temp[j] = numCnt[1] % MAX_NUM;
                } else if (j == 9) {
                    temp[j] = numCnt[8] % MAX_NUM;
                } else {
                    temp[j] = (numCnt[j - 1] + numCnt[j + 1]) % MAX_NUM;
                }
            }
            numCnt = temp;
//            System.out.println(Arrays.toString(numCnt));
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += numCnt[i];
        }
        System.out.println(result % MAX_NUM);

        br.close();
    }
}
