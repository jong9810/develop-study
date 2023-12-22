package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ_1316_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (map.get(c) == null) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(j);
                    map.put(c, temp);
                } else {
                    map.get(c).add(j);
                }
            }

            boolean isGroup = true;
            for (Character c : map.keySet()) {
                ArrayList<Integer> indexList = map.get(c);
                for (int j = 1; j < indexList.size(); j++) {
                    if (indexList.get(j) - indexList.get(j - 1) > 1) {
                        isGroup = false;
                        break;
                    }
                }
            }

            if (isGroup) count++;
        }

        System.out.println(count);
        br.close();
    }
}
