package org.example.backtracking;

import java.io.*;

// 2차원 배열을 사용하지 않고, 1차원 배열의 인덱스를 행, 값을 열로 취급하는 아이디어!
// 대각선에 위치하는지 확인하는 방법 : 행의 차와 열의 차가 같으면 대각선!
public class BOJ_9663 {
    public static int N;
    public static int[] arr;
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        nQueen(0);

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void nQueen(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;

            if (possibility(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    private static boolean possibility(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[i] == arr[col]) {
                return false;
            }
            if (Math.abs(i - col) == Math.abs(arr[i] - arr[col])) {
                return false;
            }
        }
        return true;
    }
}
