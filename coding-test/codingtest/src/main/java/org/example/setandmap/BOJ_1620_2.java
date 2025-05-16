package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1620_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int index = 0;
        Map<Integer, String> indexbook = new HashMap<>();
        Map<String, Integer> namebook = new HashMap<>();
        for (int i = 0; i < N; i++) {
            index += 1;
            String name = br.readLine();
            indexbook.put(index, name);
            namebook.put(name, index);
        }


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < M; i++) {
            String temp = br.readLine();
            boolean isNum = isNumeric(temp);

            if (isNum) {
                int findIndex = Integer.parseInt(temp);
                sb.append(indexbook.get(findIndex)).append("\n");
            } else {
                sb.append(namebook.get(temp)).append("\n");
            }
        }
        System.out.println(sb);

        br.close();
    }

    private static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) < 48) || (s.charAt(i) > 57)) {
                return false;
            }
        }
        return true;
    }
}
