package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 0; i < N.length(); i++) {
            int temp;
            if (N.charAt(i) < 'A') {
                temp = N.charAt(i) - '0';
            } else {
                temp = N.charAt(i) - 'A' + 10;
            }
            result += (int) Math.round(Math.pow(B, N.length() - i - 1)) * temp;
        }

        System.out.println(result);
        br.close();
    }
}
