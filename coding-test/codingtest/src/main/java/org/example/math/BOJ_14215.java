package org.example.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_14215 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.sort((o1, o2) -> o2 - o1);
        boolean isInvalid = list.get(0) >= (list.get(1) + list.get(2));
        if (isInvalid) list.set(0, list.get(1) + list.get(2) - 1);
        System.out.println(list.get(0) + list.get(1) + list.get(2));
        sc.close();
    }
}
