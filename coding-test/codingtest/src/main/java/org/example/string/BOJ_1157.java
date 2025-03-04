package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().toUpperCase();
        int len = word.length();

        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[word.charAt(i) - 'A']++;
        }

        int max = 0;
        char result = '?';
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
                max = count[i];
                result = (char) (i + 'A');
            } else if (max == count[i]) {
                result = '?';
            }
        }
        System.out.println(result);

        br.close();
    }
}
