package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13909_2 {
    // 방법 2. 정수를 제곱한 수 : 시간 초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (isSquareNumber(i)) result++;
        }

        System.out.println(result);
        br.close();
    }
    private static boolean isSquareNumber(int num) {
        return Math.sqrt(num) % 1 == 0.0;
    }
}
