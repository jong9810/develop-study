package org.example.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = i;
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);
        int[] result = new int[N];
        int resultNum = 0;
        int prevIdx = 0;
        int prevVal = 0;
        for (int[] ints : arr) {
            if (resultNum > 0 && prevVal == ints[1]) {
                result[ints[0]] = result[prevIdx];
            } else {
                result[ints[0]] = resultNum;
                resultNum++;
            }
            prevIdx = ints[0];
            prevVal = ints[1];
        }
        for (int ii : result) {
            sb.append(ii).append(" ");
        }
        System.out.println(sb);
    }
}
