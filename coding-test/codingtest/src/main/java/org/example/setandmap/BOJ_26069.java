package org.example.setandmap;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        set.add("ChongChong");
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();
            if (set.contains(name1) || set.contains(name2)) {
                set.add(name1);
                set.add(name2);
            }
        }
        bw.write(String.valueOf(set.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}
