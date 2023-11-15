package org.example.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int tempSum = nums[i] + nums[j] + nums[k];
                    if (tempSum > M) break;
                    else if (tempSum > sum) {
                        sum = tempSum;
                        if (sum == M) break;
                    }
                }
                if (sum == M) break;
            }
            if (sum == M) break;
        }
        System.out.println(sum);
        sc.close();
    }
}
