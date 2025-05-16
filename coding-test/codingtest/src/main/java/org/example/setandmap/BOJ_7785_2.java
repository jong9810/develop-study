package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7785_2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Set<String> remain = new HashSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String state = st.nextToken();

            if (state.equals("enter")) {
                if (remain.contains(name)) {
                    // 에러처리
                } else {
                    remain.add(name);
                }
            } else if (state.equals("leave")) {
                if (remain.contains(name)) {
                    remain.remove(name);
                } else {
                    // 에러처리
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        List<String> tempSet = new ArrayList<>(remain);
        tempSet.sort(Comparator.reverseOrder());

        for (String s : tempSet) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);

        br.close();
    }
}
