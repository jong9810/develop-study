package org.example.recursivefunction;

import java.io.*;

public class BOJ_11729 {
    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        result.append((int) Math.pow(2, N) - 1).append("\n");
        Hanoi(N, 1, 2, 3);

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * @param size : 원판의 개수
     * @param start : 출발지
     * @param mid : 옮기기 위해 이동해야 하는 장소
     * @param to : 목적지
     */
    private static void Hanoi(int size, int start, int mid, int to) {
        // 이동할 원반의 수가 1개라면?
        if (size == 1) {
            result.append(start).append(" ").append(to).append("\n");
            return;
        }

        // A -> C로 옮긴다고 가정할 때,
        // STEP 1 : N-1개를 A에서 B로 이동 (= start 지점의 N-1개의 원판을 mid 지점으로 옮긴다.)
        Hanoi(size - 1, start, to, mid);

        // STEP 2 : 1개를 A에서 C로 이동 (= start 지점의 N번째 원판을 to지점으로 옮긴다.)
        result.append(start).append(" ").append(to).append("\n");

        // STEP 3 : N-1개를 B에서 C로 이동 (= mid 지점의 N-1개의 원판을 to 지점으로 옮긴다.)
        Hanoi(size - 1, mid, start, to);
    }
}
