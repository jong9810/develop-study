package org.example.math;

import java.io.*;

public class BOJ_4134_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            long number = Long.parseLong(br.readLine());

            if (number >= 0 && number <= 2) {
                sb.append(2).append("\n");
                continue;
            }

            while (true) {
                boolean isPrime = true;
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    sb.append(number).append("\n");
                    break;
                } else {
                    number++;
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}
