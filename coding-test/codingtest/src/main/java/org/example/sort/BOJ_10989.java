package org.example.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Scanenr 로 쓰면 내부적으로 자체 정규식 검사 과정에서 시간이 엄청 소요되기 때문에 '시간 초과'가 발생할 수 밖에 없다.
// 그렇기 때문에 BufferedReader 를 쓰는 것을 디폴트로 해야한다. 또한 출력도 BufferedWriter 또는 StringBuilder 를 이용하여 출력해야한다.
public class BOJ_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
