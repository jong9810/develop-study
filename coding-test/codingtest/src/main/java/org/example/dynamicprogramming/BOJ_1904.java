package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N이 클 때 재귀 함수 호출 횟수가 많아서 StackOverFlowError가 발생했다.
// 해결방안 : N을 적절한 수로 나누어서 나눈 몫만큼 ftn()함수를 여러 번에 걸쳐 호출해주었다(그냥 제출하는 것보다 메모리와 시간 성능이 더 뛰어났음).
// 보통은 스택 사이즈가 1024KB인데 스택 사이즈를 늘려주면 StackOverFlowError가 발생하는 것을 막을 수 있다(백준 사이트에서는 StackOverFlowError 발생 X).
// 하지만 스택 사이즈를 너무 크게 설정하면 다른 프로세스에 영향을 줄 수 있으니 주의해야 한다.
public class BOJ_1904 {
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        dp[1] = 1;
        if (N >= 2) {
            dp[2] = 2;
        }

        int div = N / 20000;

        for (int i = 1; i <= div; i++) {
            ftn(20000 * i);
        }

        System.out.println(ftn(N));
        br.close();
    }

    private static int ftn(int idx) {
        if (dp[idx] != 0) {
            return dp[idx];
        }
        return dp[idx] = (ftn(idx - 2) + ftn(idx - 1)) % 15746;
    }
}
