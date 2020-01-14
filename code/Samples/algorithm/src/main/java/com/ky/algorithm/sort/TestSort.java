package com.ky.algorithm.sort;

/**
 * description:
 * author: kyXiao
 * created date: 2018/9/28 11:50
 */

public class TestSort {
    private static final String TAG = "TestSort";
    //???????->?:4-3-2-1
    private static final int INSERTION_SORT = 4;
    private static final int SELECTION_SORT = 3;
    private static final int BUBBLE_SORT = 2;
    private static final int QUICK_SORT = 1;

    private static int[] data = new int[]{2, 1, 4, 5, 10, 9, 8, 7, 1, 11, 6, 0};

    private static int SORT_TYPE = SELECTION_SORT;

    public static void main(String[] args) {

        for (int i = 1; i < 5; i++) {

            printResult();
            sort(i);
            printResult();
            data = new int[]{2, 1, 4, 5, 10, 9, 8, 7, 1, 11, 6, 0};
        }
    }

    private static void printResult() {

        for (int aData : data) {
            System.out.print(" " + aData);
        }
    }

    private static void sort(int sortType) {
        switch (sortType) {
            case INSERTION_SORT:
                insertionSort();
                break;
            case SELECTION_SORT:
                selectionSort();
                break;
            case BUBBLE_SORT:
                bubbleSort();
                break;
            case QUICK_SORT:
                quickSort(0, data.length - 1);
                //quickSort(data, 0, data.length - 1);
                break;
        }
    }


    private static void insertionSort() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }


    private static void selectionSort() {
        for (int i = 0; i < data.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < data.length; j++) {
                if (data[j] < data[k])
                    k = j;
            }
            if (i != k)
            {
                int temp = data[i];
                data[i] = data[k];
                data[k] = temp;
            }
        }
    }


    private static void bubbleSort() {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                int a = data[j];
                int b = data[j + 1];
                if (a > b) {
                    data[j] = b;
                    data[j + 1] = a;
                }
            }
        }

      /*  for (int i = 0; i < data.length; i++) { //??????????
            for (int j = i + 1; j < data.length; j++) {
                int a = data[i];
                int b = data[j];
                if (a > b) {
                    data[i] = b;
                    data[j] = a;
                }
            }
        }*/
    }

    private static void quickSort(int head, int last) {

        if (head > last) return;
        int i = head;
        int j = last;
        int key = data[head];
        while (i != j) {
            while (data[j] >= key && i < j) {
                j--;
            }
            while (data[i] <= key && i < j) {
                i++;
            }

            if (i < j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        data[head] = data[i];
        data[i] = key;

        quickSort(head, i - 1);
        quickSort(i + 1, last);
    }
}
