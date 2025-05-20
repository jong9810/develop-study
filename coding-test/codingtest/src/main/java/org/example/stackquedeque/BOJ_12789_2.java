package org.example.stackquedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int order = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());

            boolean isDetermined = false;
            if (temp == order) {
                order += 1;
                while (!isDetermined) {
                    if (stack.isEmpty() || stack.peek() != order) {
                        isDetermined = true;
                    } else {
                        stack.pop();
                        order += 1;
                    }
                }
            } else {
                stack.add(temp);
            }
        }

        boolean isPossible = true;
        while (!stack.isEmpty()) {
            if (stack.peek() != order) {
                isPossible = false;
                break;
            } else {
                stack.pop();
                order += 1;
            }
        }

        if (isPossible) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
        br.close();
    }
}
