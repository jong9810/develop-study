package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14215 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.sort((o1, o2) -> o2 - o1);

        boolean isInvalid = list.get(0) >= (list.get(1) + list.get(2));
        if (isInvalid) list.set(0, list.get(1) + list.get(2) - 1);

        System.out.println(list.get(0) + list.get(1) + list.get(2));
        br.close();
    }
}
