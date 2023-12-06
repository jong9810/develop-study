package org.example.backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2580 {
    public static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        for (int i = 0; i < board.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku();

        for (int[] row : board) {
            for (int val : row) {
                bw.write(val + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void sudoku() {

    }
}
