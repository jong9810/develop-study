package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            x.add(Integer.parseInt(st.nextToken()));
            y.add(Integer.parseInt(st.nextToken()));
        }

        int[] result = new int[2];
        for (int i = 0; i < 3; i++) {
            int xTemp = x.get(i);
            int yTemp = y.get(i);

            int xCnt = Collections.frequency(x, xTemp);
            int yCnt = Collections.frequency(y, yTemp);

            if (xCnt == 1) result[0] = xTemp;
            if (yCnt == 1) result[1] = yTemp;
        }

        System.out.println(result[0] + " " + result[1]);
        br.close();
    }
}