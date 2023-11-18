package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        String[] names = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            map.put(name, i);
            names[i] = name;
        }
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (isStringNumber(input)) {
                int index = Integer.parseInt(input);
                sb.append(names[index]).append("\n");
            } else {
                sb.append(map.get(input)).append("\n");
            }
        }
        System.out.println(sb);
    }
    private static boolean isStringNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
