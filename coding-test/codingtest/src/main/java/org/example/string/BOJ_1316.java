package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 입력될 단어의 개수
        int cnt = 0; // 결과 : 입력된 단어 중 그룹단어의 개수

        for (int i = 0; i < n; i++) {
            int[] check = new int[123]; // 단어의 아스키 코드(알파벳 소문자: 97 ~ 122) 저장.
            String word = br.readLine();
            boolean isGroup = true;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (check[c] == 0) {
                    check[c] = 1;
                    while (word.charAt(j) == c) {
                        j++;
                        if (j == word.length()) break;
                    }
                    j -= 1;
                    if ((j != word.length() - 1) && word.indexOf(c, j + 1) != -1) {
                        isGroup = false;
                        break;
                    }
                }
            }
            if (isGroup) cnt++;
        }
        System.out.println(cnt);
        br.close();
    }
}
