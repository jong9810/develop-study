package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] arr = new String[5];
        for (int i = 0; i < arr.length; i++) {
            String line = br.readLine();
            arr[i] = line;
        }

        for (int i = 0; i < 15; i++) {
            for (String s : arr) {
                if (i >= s.length()) continue;
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb);
        br.close();
    }
}
