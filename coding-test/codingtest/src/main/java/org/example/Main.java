package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            int[] idxArr = new int[26];
            boolean isGroup = true;
            for (int j = 1; j <= word.length(); j++) {
                int alp = word.charAt(j - 1) - 'a';
                if (idxArr[alp] == 0 || idxArr[alp] + 1 == j) {
                    idxArr[alp] = j;
                } else {
                    isGroup = false;
                    break;
                }
            }

            if (isGroup) {
                count++;
            }
        }
        System.out.println(count);

        br.close();
    }
}
