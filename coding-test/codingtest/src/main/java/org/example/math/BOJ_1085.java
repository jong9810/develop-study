package org.example.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_1085 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        List<Integer> distance = new ArrayList<>();
        distance.add(x);
        distance.add(y);
        distance.add(Math.abs(x - w));
        distance.add(Math.abs(y - h));
        distance.sort((o1, o2) -> o1 - o2);
        System.out.println(distance.get(0));
        sc.close();
    }
}
