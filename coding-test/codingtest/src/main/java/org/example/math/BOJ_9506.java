package org.example.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_9506 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) break;

            List<Integer> list = new ArrayList<>();
            list.add(1);
            int sum = 1;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    list.add(i);
                    sum += i;
                    // 예를 들어, 6 = 2 * 3이면 i는 2이고, n / i는 3인 셈이다.
                    if (i != n / i) {
                        list.add(n / i);
                        sum += n / i;
                    }
                }
            }
            list.sort((o1, o2) -> o1 - o2);

            sb.append(n);
            if (sum == n) {
                sb.append(" = ");
                for (int i = 0; i < list.size(); i++) {
                    if (i != list.size() - 1) {
                        sb.append(list.get(i)).append(" + ");
                    } else {
                        sb.append(list.get(i)).append("\n");
                    }
                }
            } else {
                sb.append(" is NOT perfect.\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}
