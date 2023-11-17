package org.example.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        while (true) {
            list.add(N % 10);
            N /= 10;
            if (N == 0) break;
        }
        list.sort((o1, o2) -> o2 - o1);
        list.stream().forEach(i -> sb.append(i));
        System.out.println(sb);
    }
}
