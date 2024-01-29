package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13909_1 {
    // 방법 1. 브루트 포스 : 메모리 초과
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
}
