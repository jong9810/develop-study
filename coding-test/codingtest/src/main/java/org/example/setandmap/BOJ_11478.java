package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int len = word.length();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++){
                result.add(word.substring(i, j));
            }
        }
        System.out.println(result.size());
    }
}
