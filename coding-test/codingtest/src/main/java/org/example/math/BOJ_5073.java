package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int l1 = Integer.parseInt(st.nextToken());
            int l2 = Integer.parseInt(st.nextToken());
            int l3 = Integer.parseInt(st.nextToken());

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

            if (isInvalid) {
                System.out.println("Invalid");
            } else if (isEquilateral) {
                System.out.println("Equilateral");
            } else if (isIsosceles) {
                System.out.println("Isosceles");
            } else if (isScalene) {
                System.out.println("Scalene");
            }
        }

        br.close();
    }
}
