package org.example.recursivefunction;

import java.io.*;

public class BOJ_2447 {
    public static boolean[][] arr;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new boolean[N][N];
        ftn(N);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j]) result.append(" ");
                else result.append("*");
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void ftn(int size) {
        if (size == 1) return;
        int newSize = size / 3;

        for (int i = 0; i < N; i += size) {
            for (int j = 0; j < N; j += size) {
                for (int r = i + newSize; r < i + 2 * newSize; r++) {
                    for (int c = j + newSize; c < j + 2 * newSize; c++) {
                        arr[r][c] = true;
                    }
                }
            }
        }

        ftn(newSize);
    }

    /*/ 방법 1. 시간초과
    // 첫 번째 ftn()을 수행한 다음부터는 재귀함수가 N ^ 2 만큼 호출돼서 시간초과가 난 거 같다(N은 3의 배수).
    public static boolean[][] arr;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new boolean[N][N];
        ftn(0, 0, N);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j]) result.append(" ");
                else result.append("*");
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void ftn(int row, int col, int size) {
        if (size == 1) return;
        int newSize = size / 3;

        for (int i = row + newSize; i < row + 2 * newSize; i++) {
            for (int j = col + newSize; j < col + 2 * newSize; j++) {
                arr[i][j] = true;
            }
        }

        for (int i = 0; i < N; i += newSize) {
            for (int j = 0; j < N; j += newSize) {
                ftn(i, j, newSize);
            }
        }
    }
    */
}
