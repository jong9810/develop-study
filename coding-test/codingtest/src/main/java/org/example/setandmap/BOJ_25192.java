package org.example.setandmap;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BOJ_25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.equals("ENTER")) {
                map.clear();
            } else if (map.get(str) == null) {
                map.put(str, i);
                cnt++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
