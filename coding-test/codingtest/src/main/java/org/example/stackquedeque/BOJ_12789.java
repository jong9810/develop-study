package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int order = 1;
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }
        while (!q.isEmpty()) {
            if (q.peek() != order) {
                if (!stack.isEmpty() && stack.peek() == order) {
                    stack.pop();
                    order++;
                } else {
                    stack.push(q.poll());
                }
            } else {
                q.remove();
                order++;
            }
        }
        System.out.println(checkOrder(order, stack));
        br.close();
    }
    private static String checkOrder(int order, Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            if (stack.peek() == order) {
                stack.pop();
                order++;
            } else {
                return "Sad";
            }
        }
        return "Nice";
    }
}
