package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// LinkedList와 ArrayDeque 차이
// https://velog.io/@newdana01/Java-%ED%81%90-%EA%B5%AC%ED%98%84%EC%8B%9C-ArrayDeque%EC%99%80-LinkedList-%EC%84%B1%EB%8A%A5-%EC%B0%A8%EC%9D%B4-Deque-Queue-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4
public class BOJ_2346 {
    // 방법 1.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            dq.add(new int[] {i, Integer.parseInt(st.nextToken())});
        }
        sb.append(dq.peek()[0]).append(" ");
        int move = dq.poll()[1];
        while (!dq.isEmpty()) {
            if (move > 0) {
                for (int i = 1; i < move; i++) {
                    dq.add(dq.poll());
                }
                sb.append(dq.peek()[0]).append(" ");
                move = dq.poll()[1];
            } else if (move < 0) {
                for (int i = 1; i < -move; i++) {
                    dq.addFirst(dq.pollLast());
                }
                sb.append(dq.peekLast()[0]).append(" ");
                move = dq.pollLast()[1];
            }
        }
        System.out.println(sb);
        br.close();
    }
    /*/ 방법 2.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        int[] moves = new int[N];
        for (int i = 0; i < N; i++) {
            dq.addLast(i);
            moves[i] = Integer.parseInt(st.nextToken());
        }
        int move = moves[dq.peekFirst()];
        sb.append(dq.pollFirst() + 1).append(" ");
        while (!dq.isEmpty()) {
            if (move > 0) {
                for (int i = 0; i < move - 1; i++) {
                    dq.addLast(dq.pollFirst());
                }
                move = moves[dq.peekFirst()];
                sb.append(dq.pollFirst() + 1).append(" ");
            } else if (move < 0) {
                for (int i = 0; i < -move - 1; i++) {
                    dq.addFirst(dq.pollLast());
                }
                move = moves[dq.peekLast()];
                sb.append(dq.pollLast() + 1).append(" ");
            }
        }
        System.out.println(sb);
        br.close();
    }
    */
}
