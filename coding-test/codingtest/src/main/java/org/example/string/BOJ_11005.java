package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11005 {
    public static int N;
    public static int B;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        while (N / B > 0) {
            ftn();
            N /= B;
        }
        ftn();

        System.out.println(sb.reverse());
        br.close();
    }

    private static void ftn() {
        if (N % B > 9) {
            char temp = (char) (N % B + 55);
            sb.append(temp);
        } else {
            sb.append(N % B);
        }
    }
}
