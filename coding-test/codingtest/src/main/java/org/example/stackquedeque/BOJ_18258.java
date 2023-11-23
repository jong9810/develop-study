package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
//        Deque<Integer> dq = new LinkedList<>();
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if (order.equals("push")) {
                dq.add(Integer.parseInt(st.nextToken()));
            } else if (order.equals("pop")) {
                sb.append(dq.isEmpty() ? -1 : dq.pop()).append("\n");
            } else if (order.equals("size")) {
                sb.append(dq.size()).append("\n");
            } else if (order.equals("empty")) {
                sb.append(dq.isEmpty() ? 1 : 0).append("\n");
            } else if (order.equals("front")) {
                sb.append(dq.isEmpty() ? -1 : dq.peek()).append("\n");
            } else if (order.equals("back")) {
                sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
