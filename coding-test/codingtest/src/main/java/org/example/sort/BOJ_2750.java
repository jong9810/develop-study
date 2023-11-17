package org.example.sort;

import java.util.Scanner;

public class BOJ_2750 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
//        selectionSort(nums);
//        bubbleSort(nums);
//        insertionSort(nums);
        shellSort(nums);
        for (int i = 0; i < N; i++) {
            System.out.println(nums[i]);
        }
        sc.close();
    }
    // 4. 쉘 정렬
    // * 시간 복잡도
    // 1) worst : O(N^2)
    // 2) average : O(N^1.5)
    // 3) best : O(N)
    private static void shellSort(int[] arr) {
        for (int h = arr.length / 2; h > 0; h /= 2) {
            for (int i = h; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - h;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = temp;
            }
        }
    }

    // 3. 삽입 정렬
    // * 평균과 최악의 시간복잡도는 O(n^2)이고 best일 때는 O(N)이다.
    // * 삽입 정렬은 중복된 키 값의 순서가 정렬 후에도 유지되므로 stable 정렬이다.
    // * 선택 정렬이나 버블 정렬에 비해 상대적으로 빠르다.
    private static void insertionSort(int[] arr) {
        int key = 0;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 2. 버블 정렬 : 시간 복잡도는 worst, average, best 모두 O(N^2)로 동일.
    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 1. 선택 정렬 : 시간 복잡도는 worst, average, best 모두 O(N^2)로 동일.
    private static void selectionSort(int[] arr) {
        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
