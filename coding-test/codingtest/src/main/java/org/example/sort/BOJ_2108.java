package org.example.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[8001];
        int[] arr2 = new int[N];
        int sum = 0;
        int max = -4001;
        int min = 4001;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            arr[num + 4000] += 1;
            arr2[i] = num;
            if (num > max) max = num;
            if (num < min) min = num;
        }
        Arrays.sort(arr2);
        int maxCnt = 0;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (arr[i] > 0 && arr[i] > maxCnt) maxCnt = arr[i];
        }
        List<Integer> maxCntList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == maxCnt) maxCntList.add(i - 4000);
        }
        maxCntList.sort((o1, o2) -> o1 - o2);
        int median = arr2[N / 2];
        int mode = maxCntList.size() > 1 ? maxCntList.get(1) : maxCntList.get(0);
        bw.write((int) Math.round(sum * 1.0 / N) + "\n");
        bw.write(median + "\n");
        bw.write(mode + "\n");
        bw.write(String.valueOf(max - min));
        bw.flush();
        bw.close();
        br.close();
    }
}
