package org.example.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (!list.contains(s)) list.add(s);
        }
        list.sort((o1, o2) -> o1.length() == o2.length() ?
                o1.compareTo(o2) :
                o1.length() - o2.length());
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
