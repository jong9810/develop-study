package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

// 3개 이상 수의 최대공약수 구하는 알고리즘을 구현해보았다.
public class BOJ_2485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        HashSet<Integer> diffs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
            if (i > 0) diffs.add(input[i] - input[i - 1]);
        }

        int gcd = calculateGcd2(diffs);

        System.out.println((input[N - 1] - input[0]) / gcd - N + 1);
        br.close();
    }

    private static int calculateGcd(int a, int b) {
        if (a == b) return a;
        int min = Math.min(a, b);
        int gcd = 1;
        for (int i = min; i > 1; i--) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
                break;
            }
        }
        return gcd;
    }

    private static int calculateGcd2(HashSet<Integer> nums) {
        if (nums.contains(1)) return 1;
        Iterator<Integer> iter = nums.iterator();
        int num1 = iter.next();
        while (iter.hasNext()) {
            int num2 = iter.next();
            num1 = calculateGcd(num1, num2);
        }
        return num1;
    }
}
