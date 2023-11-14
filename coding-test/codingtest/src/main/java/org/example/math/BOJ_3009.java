package org.example.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_3009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            x.add(sc.nextInt());
            y.add(sc.nextInt());
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
        sc.close();
    }
}
