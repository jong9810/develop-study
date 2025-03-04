package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941 {
    private static final String[] C_WORD = {"c=", "c-", "d-", "lj", "nj", "s=", "z="};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        int len = word.length();
        for (int i = 0; i < word.length(); i++) {
            for (String s : C_WORD) {
                if (word.startsWith("dz=", i)) {
                    len -= 2;
                    i += 2;
                } else if (word.startsWith(s, i)) {
                    len -= 1;
                    i += 1;
                }
            }
        }
        System.out.println(len);

        br.close();
    }
}
