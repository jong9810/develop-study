package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13909 {
    /*/ 방법 1. 브루트 포스 : 메모리 초과
    public static boolean[] window;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        window = new boolean[N + 1];
        openOrClose();
        int cnt = 0;
        for (boolean b : window) {
            if (b) cnt++;
        }
        System.out.println(cnt);
        br.close();
    }
    private static void openOrClose() {
        for (int i = 1; i < window.length; i++) {
            int j = 1;
            while (true) {
                int tempI = i * j;
                if (tempI >= window.length) break;
                window[tempI] = !window[tempI];
                j++;
            }
        }
    }
    */
    /*/ 방법 2. 정수를 제곱한 수 : 시간 초과
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
    */
    // 방법 3. N의 제곱근 값을 버림한 정수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println((int) Math.floor(Math.sqrt(N)));
        br.close();
    }
}
