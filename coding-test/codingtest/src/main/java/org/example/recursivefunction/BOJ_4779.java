package org.example.recursivefunction;

import java.io.*;

public class BOJ_4779 {
    public static int N;
    public static StringBuilder result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            N = Integer.parseInt(input);
            int len = (int) Math.pow(3, N);
            result = new StringBuilder();
            result.append("-".repeat(len));
            ftn(0, len);
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void ftn(int start, int size) {
        if (size == 1) return;
        int newSize = size / 3;
        for (int i = start + newSize; i < start + 2 * newSize; i++) {
            result.setCharAt(i, ' ');
        }

        ftn(start, newSize);
        ftn(start + 2 * newSize, newSize);
    }
}
