package org.example.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_9506 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(true) {
            int n = sc.nextInt();
            if (n == -1) break;
            sb.append(n + " ");
            List<Integer> list = new ArrayList<>();
            list.add(1);
            int sum = 1;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    list.add(i);
                    sum += i;
                    if (i != n / i) {
                        list.add(n / i);
                        sum += n / i;
                    }
                }
            }
            list.sort((o1, o2) -> o1 - o2);
            if (sum == n) {
                sb.append("= ");
                for (int i = 0; i < list.size(); i++) {
                    if (i != list.size() - 1) sb.append(list.get(i) + " + ");
                    else sb.append(list.get(i) + "\n");
                }
            } else {
                sb.append("is NOT perfect.\n");
            }
        }
        System.out.println(sb);
        sc.close();
    }
}
