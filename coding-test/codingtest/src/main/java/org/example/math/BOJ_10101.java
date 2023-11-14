package org.example.math;

import java.util.Scanner;

public class BOJ_10101 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        int a3 = sc.nextInt();
        int sum = a1 + a2 + a3;
        boolean isEquilateral = a1 == a2 && a2 == a3 && a3 == 60;
        boolean isIsosceles = sum == 180 && (a1 == a2 || a1 == a3 || a2 == a3);
        boolean isScalene = sum == 180 && (a1 != a2 && a2 != a3 && a1 != a3);
        boolean isError = sum != 180;
        if (isEquilateral) System.out.println("Equilateral");
        else if (isIsosceles) System.out.println("Isosceles");
        else if (isScalene) System.out.println("Scalene");
        else if (isError) System.out.println("Error");
        sc.close();
    }
}
