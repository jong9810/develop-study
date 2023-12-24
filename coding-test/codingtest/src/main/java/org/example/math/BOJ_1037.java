package org.example.math;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort((o1, o2) -> o2 - o1);

        System.out.println(list.get(0) * list.get(N - 1));
        br.close();
    }
}
