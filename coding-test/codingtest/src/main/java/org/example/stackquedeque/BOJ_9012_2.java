package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        Stack<Character> stack;

        for (int i = 0; i < N; i++) {
            stack = new Stack<>();
            String s = br.readLine();

            boolean isDetermined = false;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    stack.push('(');
                } else {
                    if (stack.isEmpty()) {
                        isDetermined = true;
                        sb.append("NO").append("\n");
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (!isDetermined) {
                if (stack.isEmpty()) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
