package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀함수에서 return 키워드는 현재 단계를 호출했던 전 단계의 코드가 실행된 직후로 돌아가는 것을 의미한다.
public class BOJ_2580_2 {
    public static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);

        br.close();
    }

    private static void sudoku(int row, int col) {
        // 현재 행의 모든 열을 채웠을 경우 다음 행으로 이동.
        if (col == 9) {
            sudoku(row + 1, 0);
            return; // 다음 행으로 이동만 하고 나머지 동작은 수행하지 않기 위해 return 해줌.
        }

        // 모든 행을 다 채웠을 경우 결과를 출력.
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        // 빈칸인 경우
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                // 1 ~ 9까지 값 중 빈칸에 들어갈 수 있으면 빈칸에 넣고 다음 칸으로 이동.
                if (possibility(row, col, i)) {
                    board[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            // 1 ~ 9까지 값 중 어떤 값도 빈칸에 들어갈 수 없으면 채웠던 값을 없애고 그 전 빈칸으로 이동.
            board[row][col] = 0;
            return;
        }

        // 빈칸이 아닌 경우 다음 칸으로 이동.
        sudoku(row, col + 1);
    }

    private static boolean possibility(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }

            if (board[i][col] == val) {
                return false;
            }
        }

        int rStart = row / 3 * 3;
        int cStart = col / 3 * 3;
        for (int i = rStart; i < rStart + 3; i++) {
            for (int j = cStart; j < cStart + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }
}
