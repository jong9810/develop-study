package org.example;

import java.util.Scanner;

public class    BOJ_25206 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 20; // 과목 개수

        float avgGrade = 0;
        int div = 0;
        for (int i = 0; i < n; i++) {
            sc.next();
            float time = sc.nextFloat(); // 학점(ex. 3.0)
            String sGrade = sc.next(); // 과목평점(ex. A0, P)

            if (sGrade.equals("P")) continue;

            float fGrade = checkGrade(sGrade);
            div += time;
            avgGrade += time * fGrade;
        }
        System.out.println(avgGrade / div);
        sc.close();
    }

    private static float checkGrade(String sGrade) {
        float fGrade = 0f;
        if (sGrade.equals("A+")) {
            fGrade = 4.5f;
        } else if (sGrade.equals("A0")) {
            fGrade = 4.0f;
        } else if (sGrade.equals("B+")) {
            fGrade = 3.5f;
        } else if (sGrade.equals("B0")) {
            fGrade = 3.0f;
        } else if (sGrade.equals("C+")) {
            fGrade = 2.5f;
        } else if (sGrade.equals("C0")) {
            fGrade = 2.0f;
        } else if (sGrade.equals("D+")) {
            fGrade = 1.5f;
        } else if (sGrade.equals("D0")) {
            fGrade = 1.0f;
        } else if (sGrade.equals("F")) {
            fGrade = 0.0f;
        }
        return fGrade;
    }
}
