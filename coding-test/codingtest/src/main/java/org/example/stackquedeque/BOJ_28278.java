package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// Stack 클래스 : push, pop, peek, empty, search 메서드 등 지원.
public class BOJ_28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int tc = 0; tc < N; tc++) {
            String order = br.readLine();
            if (order.length() > 1) {
                st = new StringTokenizer(order);
                st.nextToken();
                stack.push(Integer.parseInt(st.nextToken()));
            } else {
                int orderInt = Integer.parseInt(order);
                int result = -2;
                if (orderInt == 2) {
                    result = stack.empty() ? -1 : stack.pop();
                } else if (orderInt == 3) {
                    result = stack.size();
                } else if (orderInt == 4) {
                    result = stack.empty() ? 1 : 0;
                } else if (orderInt == 5) {
                    result = stack.empty() ? -1 : stack.peek();
                }
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
