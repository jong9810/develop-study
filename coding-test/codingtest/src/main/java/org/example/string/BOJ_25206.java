package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 20; // 과목 개수
        float avgGrade = 0;
        float div = 0;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            float time = Float.parseFloat(st.nextToken()); // 학점(ex. 3.0)
            String sGrade = st.nextToken(); // 과목평점(ex. A0, P)

            if (sGrade.equals("P")) continue;

            float fGrade = checkGrade(sGrade);
            div += time;
            avgGrade += time * fGrade;
        }
        System.out.println(avgGrade / div);
        br.close();
    }

    private static float checkGrade(String sGrade) {
        /* return switch (sGrade) {
            case "A+" -> 4.5f;
            case "A0" -> 4.0f;
            case "B+" -> 3.5f;
            case "B0" -> 3.0f;
            case "C+" -> 2.5f;
            case "C0" -> 2.0f;
            case "D+" -> 1.5f;
            case "D0" -> 1.0f;
            case "F" -> 0.0f;
            default -> 0f;
        }; */

        float fGrade = 0f;
        switch (sGrade) {
            case "A+": fGrade = 4.5f; break;
            case "A0": fGrade = 4.0f; break;
            case "B+": fGrade = 3.5f; break;
            case "B0": fGrade = 3.0f; break;
            case "C+": fGrade = 2.5f; break;
            case "C0": fGrade = 2.0f; break;
            case "D+": fGrade = 1.5f; break;
            case "D0": fGrade = 1.0f; break;
            case "F": fGrade = 0.0f; break;
            default: fGrade = 0f;
        }
        return fGrade;
    }
}
