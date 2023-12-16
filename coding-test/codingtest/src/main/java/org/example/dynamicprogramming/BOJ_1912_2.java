package org.example.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 숫자 배열의 0번 부터 특정 인덱스까지 합을 배열로 저장한 코드 : 시간초과
public class BOJ_1912_2 {
    public static int N;
    public static int max = Integer.MIN_VALUE;
    public static int[] arr;
    public static int[] sumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sumArr = new int[N];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            sum += num;
            sumArr[i] = sum;
        }

        int tempSum = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                tempSum = continuitySum(i, j);
                max = Math.max(tempSum, max);
            }
        }

        System.out.println(max);
        br.close();
    }

    private static int continuitySum(int start, int end) {
        if (start - 1 < 0) {
            return sumArr[end];
        }
        return sumArr[end] - sumArr[start - 1];
    }
}
