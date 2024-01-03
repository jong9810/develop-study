package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3009_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            int tempX = Integer.parseInt(st.nextToken());
            int tempY = Integer.parseInt(st.nextToken());

            if (xMap.get(tempX) == null) {
                xMap.put(tempX, 1);
            } else {
                xMap.put(tempX, 2);
            }

            if (yMap.get(tempY) == null) {
                yMap.put(tempY, 1);
            } else {
                yMap.put(tempY, 2);
            }
        }

        Set<Integer> xKey = xMap.keySet();
        Set<Integer> yKey = yMap.keySet();

        for (Integer key : xKey) {
            int x = xMap.get(key);

            if (x == 1) {
                sb.append(key).append(" ");
            }
        }

        for (Integer key : yKey) {
            int y = yMap.get(key);

            if (y == 1) {
                sb.append(key);
            }
        }

        System.out.println(sb);
        br.close();
    }
}