package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }
        Iterator<String> iter = map.keySet().iterator();
        List<String> list = new ArrayList<>();
        while (iter.hasNext()) {
            String key = iter.next();
            String val = map.get(key);
            if (val.equals("enter")) list.add(key);
        }
        list.sort((o1, o2) -> o2.compareTo(o1));
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
