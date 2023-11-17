package org.example.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, name));
        }
        list.sort(((o1, o2) -> {
            if (o1.age == o2.age) {
                return o1.order - o2.order;
            }
            return o1.age - o2.age;
        } ));
        for (Member member : list) {
            sb.append(member.getAge()).append(" ").append(member.getName()).append("\n");
        }
        System.out.println(sb);
    }
    private static class Member {
        private int age;
        private String name;
        private int order = 0;

        public Member(int age, String name) {
            this.age = age;
            this.name = name;
            order++;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
