package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_28278_2 {
    static StringBuffer sb = new StringBuffer();
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            executeOrder(br.readLine());
        }
        System.out.println(sb);

        br.close();
    }

    private static void executeOrder(String order) {
        char c = order.charAt(0);

        switch (c) {
            case '1': stack.push(Integer.parseInt(order.substring(2))); break;
            case '2': sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n"); break;
            case '3': sb.append(stack.size()).append("\n"); break;
            case '4': sb.append(stack.isEmpty() ? 1 : 0).append("\n"); break;
            case '5': sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n"); break;
            default: break;
        }
    }
}
