package org.example.recursivefunction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// temp 배열을 전역 변수로 선언하면 시간초과가 뜨지 않는데 함수 내에서 선언하면 시간초과가 떴다.
// merge 함수가 여러 번 호출되어서 temp 배열을 계속 생성했다가 지웠다가 반복해서 시간초과가 떴나?
public class BOJ_24060 {
    /*/ 방법 1. 병합정렬 직접 수행하고 저장되는 결과를 List에 저장하기.
    public static List<Integer> result = new ArrayList<>();
    public static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        temp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(arr, 0, arr.length - 1);

        if (K > result.size()) {
            bw.write("-1");
        } else if (K > 0) {
            bw.write(String.valueOf(result.get(K - 1)));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p; int j = q + 1; int t = 0;

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) temp[t++] = arr[i++];
            else temp[t++] = arr[j++];
        }

        while (i <= q)
            temp[t++] = arr[i++];

        while (j <= r)
            temp[t++] = arr[j++];

        i = p; t = 0;

        while (i <= r) {
            result.add(temp[t]);
            arr[i++] = temp[t++];
        }
    }
    */
    // 방법 2. 저장된 결과의 개수를 세서 K번째에 저장되는 결과를 추출하기.
    public static int K;
    public static int cnt;
    public static int result = -1;
    public static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        temp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(arr, 0, arr.length - 1);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p; int j = q + 1; int t = 0;

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) temp[t++] = arr[i++];
            else temp[t++] = arr[j++];
        }

        while (i <= q)
            temp[t++] = arr[i++];

        while (j <= r)
            temp[t++] = arr[j++];

        i = p; t = 0;

        while (i <= r) {
            cnt++;
            if (cnt == K) {
                result = temp[t];
                break;
            }
            arr[i++] = temp[t++];
        }
    }
}
