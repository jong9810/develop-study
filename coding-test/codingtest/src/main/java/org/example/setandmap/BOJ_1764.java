package org.example.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 참고 : map.get(key); // map에 key가 없을 경우 null 반환
// map.get(input) != null 로 값이 존재하는지 확인하는 방법이
// list.contains(input) 보다 훨씬 빠르다.
// ArrayList.contains() 시간복잡도 : O(N)
// HashSet.contains() 시간복잡도 : O(1)
// HashMap.get() 시간복잡도 : O(1)
public class BOJ_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1. HashMap 사용
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), 0);
        }
        int cnt = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (map.get(input) != null) {
                cnt++;
                result.add(input);
            }
        }
        sb.append(cnt).append("\n");
        result.sort(String::compareTo);
        for (String s : result) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);

        /*/ 2. HashSet 사용
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        int cnt = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (set.contains(input)) {
                cnt++;
                result.add(input);
            }
        }
        sb.append(cnt).append("\n");
        result.sort(String::compareTo);
        for (String s : result) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
        */
    }
}
