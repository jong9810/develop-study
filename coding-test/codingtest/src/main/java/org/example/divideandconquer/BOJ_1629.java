package org.example.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 결과 : 메모리 초과
public class BOJ_1629 {

    private static int result = 1;
    private static int cnt;
    private static int BASE;
    private static int POW;
    private static int DIV;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        BASE = Integer.parseInt(st.nextToken());
        POW = Integer.parseInt(st.nextToken());
        DIV = Integer.parseInt(st.nextToken());

        recur();
        System.out.println(result);

        br.close();
    }

    private static void recur() {
        if (cnt >= POW) {
            return;
        }
        cnt++;
        result = (result * BASE) % DIV;
        recur();
    }
}
