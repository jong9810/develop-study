package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4949_2 {
        static Stack<Character> stack;
        static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while (!(line = br.readLine()).equals(".")) {
             stack = new Stack<>();

            boolean isDetermined = false;
            for (int i = 0; i < line.length(); i++) {
                char temp = line.charAt(i);
                if (temp == '(' || temp == '[') {
                    stack.add(temp);
                } else if (temp == ')' || temp == ']') {
                    int open = returnOpen(temp);
                    if (stack.isEmpty() || stack.peek() != open) {
                        isDetermined = true;
                        sb.append("no").append("\n");
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (!isDetermined) {
                if (stack.isEmpty()) {
                    sb.append("yes").append("\n");
                } else {
                    sb.append("no").append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static int returnOpen(char close) {
        int open = close;
        if (close == ')') {
            open -= 1;
        } else if (close == ']') {
            open -= 2;
        }
        return open;
    }
}
