package org.example.backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2580. 스도쿠 문제(내가 짠 코드) : 틀렸습니다(예제는 맞게 나옴).
public class BOJ_2580 {
    public static int[][] board = new int[9][9];
    public static int varCnt;
    public static boolean isPrinted = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int[] firstVarPos = new int[]{-1, -1};
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    varCnt++;
                    if (firstVarPos[0] == -1) {
                        firstVarPos[0] = i;
                        firstVarPos[1] = j;
                    }
                }
            }
        }

        ArrayList<Integer> numList = searchPossibleValue(firstVarPos[0], firstVarPos[1]);
        for (Integer num : numList) {
            sudoku(firstVarPos[0], firstVarPos[1], num);
        }

        br.close();
    }

    private static void sudoku(int row, int col, int num) {
        if (varCnt == 0) { // 모든 빈칸을 채운 경우 결과 출력
            if (!isPrinted) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println();
                }
                isPrinted = true;
            }
            return;
        }

        varCnt--;
        board[row][col] = num;
        if (checkPossibility(row, col)) {
            int[] nextBlankPos = searchNextBlank(row, col); // 다음 빈칸의 위치를 찾음
            if (nextBlankPos[0] != -1) { // 다음 빈칸이 존재하는 경우
                ArrayList<Integer> nextBlankNumList = searchPossibleValue(nextBlankPos[0], nextBlankPos[1]);

                System.out.println(varCnt + "--------------------------------");
                System.out.println("row, col, num : " + row + ", " + col + ", " + num);
                System.out.println("checkPossibility : " + checkPossibility(row, col));
                System.out.println("nextBlankPos : " + Arrays.toString(searchNextBlank(row, col)));
                System.out.println("nextBlankNumList : " + nextBlankNumList);

                for (Integer nextBlankNum : nextBlankNumList) {
                    sudoku(nextBlankPos[0], nextBlankPos[1], nextBlankNum);
                }
            }
        } else {
            board[row][col] = 0;
            varCnt++;
        }
    }

    private static int[] searchNextBlank(int row, int col) {
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == row && j <= col) continue;
                if (board[i][j] == 0){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] countNumber(int row, int col) {
        int[] result = new int[10];
        for (int i = 0; i < 9; i++) {
            result[board[i][col]]++;
        }

        for (int i = 0; i < 9; i++) {
            result[board[row][i]]++;
        }

        int rStart = row / 3 * 3;
        int cStart = col / 3 * 3;
        for (int i = rStart; i < rStart + 3; i++) {
            for (int j = cStart; j < cStart + 3; j++) {
                result[board[i][j]]++;
            }
        }

        result[0] = 0;
        return result;
    }

    private static boolean checkPossibility(int row, int col) {
        int[] arr = countNumber(row, col);
        for (int cnt : arr) {
            if (cnt > 3) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> searchPossibleValue(int row, int col) {
        int[] arr = countNumber(row, col);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (arr[i] < 3) {
                result.add(i);
            }
        }
        return result;
    }
}
