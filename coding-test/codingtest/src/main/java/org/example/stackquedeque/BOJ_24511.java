package org.example.stackquedeque;

import java.io.*;
import java.util.*;

// 문제에서 N * M 
public class BOJ_24511 {
    /*/ 방법 1. Queue를 최악의 경우 N개 생성해서 N * M 반복문을 수행함(최악의 경우 100,000 * 100,000번 반복, 시간초과).
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        List<Queue<Integer>> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int dType = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st2.nextToken());
            if (dType == 0) {
                Queue<Integer> q = new ArrayDeque<>();
                q.add(value);
                list.add(q);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(st.nextToken());
            for (Queue<Integer> q : list) {
                q.add(val);
                val = q.poll();
            }
            sb.append(val).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
    */
    
    // 방법 2. Deque 사용해서 queue를 최악의 경우 N개 생성하는 것을 막음.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (arr[i] == 0) {
                dq.addLast(num);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            dq.addFirst(Integer.parseInt(st.nextToken()));
            bw.write(dq.pollLast() + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
