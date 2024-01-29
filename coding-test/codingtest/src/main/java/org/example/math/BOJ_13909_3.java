package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13909_3 {
    // 방법 3. N의 제곱근 값을 버림한 정수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println((int) Math.floor(Math.sqrt(N)));
        br.close();
    }
}
