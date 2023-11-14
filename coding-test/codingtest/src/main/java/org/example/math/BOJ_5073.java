package org.example.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_5073 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int l1 = sc.nextInt();
            int l2 = sc.nextInt();
            int l3 = sc.nextInt();
            if (l1 == l2 && l2 == l3 && l3 == 0) break;
            List<Integer> list = new ArrayList<>();
            list.add(l1);
            list.add(l2);
            list.add(l3);
            list.sort((o1, o2) -> o2 - o1);
            boolean isInvalid = list.get(0) >= (list.get(1) + list.get(2));
            boolean isEquilateral = l1 == l2 && l2 == l3;
            boolean isIsosceles = (l1 == l2) || (l2 == l3) || (l1 == l3);
            boolean isScalene = l1 != l2 && l1 != l3 && l2 != l3;
            if (isInvalid) System.out.println("Invalid");
            else if (isEquilateral) System.out.println("Equilateral");
            else if (isIsosceles) System.out.println("Isosceles");
            else if (isScalene) System.out.println("Scalene");
        }
        sc.close();
    }
}
