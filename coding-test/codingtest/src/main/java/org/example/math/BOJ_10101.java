package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10101 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a1 = Integer.parseInt(br.readLine());
        int a2 = Integer.parseInt(br.readLine());
        int a3 = Integer.parseInt(br.readLine());

        int sum = a1 + a2 + a3;

        boolean isEquilateral = a1 == a2 && a2 == a3 && a3 == 60;
        boolean isIsosceles = sum == 180 && (a1 == a2 || a1 == a3 || a2 == a3);
        boolean isScalene = sum == 180 && (a1 != a2 && a2 != a3 && a1 != a3);
        boolean isError = sum != 180;

        if (isEquilateral) {
            System.out.println("Equilateral");
        } else if (isIsosceles) {
            System.out.println("Isosceles");
        } else if (isScalene) {
            System.out.println("Scalene");
        } else if (isError) {
            System.out.println("Error");
        }

        br.close();
    }
}
