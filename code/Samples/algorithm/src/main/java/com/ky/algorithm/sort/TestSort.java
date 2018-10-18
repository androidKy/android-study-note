package com.ky.algorithm.sort;

/**
 * description: 排序算法
 * author: kyXiao
 * created date: 2018/9/28 11:50
 */

public class TestSort {
    private static final String TAG = "TestSort";
    //时间复杂度大->小:4-3-2-1
    private static final int INSERTION_SORT = 4;    //时间复杂度为O(N^2)，空间复杂度是O(1)
    private static final int SELECTION_SORT = 3;    //时间复杂度为O(N^2)
    private static final int BUBBLE_SORT = 2;   //
    private static final int QUICK_SORT = 1;    //平均时间复杂度为O(NlogN)

    private static int[] data = new int[]{2, 1, 4, 5, 10, 9, 8, 7, 1, 11, 6, 0};

    private static int SORT_TYPE = SELECTION_SORT;

    public static void main(String[] args) {

        for (int i = 1; i < 5; i++) {
            System.out.println("");
            System.out.println("排序前：");
            printResult();
            System.out.println("");
            sort(i);
            System.out.println("后：");
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
                System.out.print("插入排序");
                insertionSort();
                break;
            case SELECTION_SORT:
                System.out.print("选择排序");
                selectionSort();
                break;
            case BUBBLE_SORT:
                System.out.print("冒泡排序");
                bubbleSort();
                break;
            case QUICK_SORT:
                System.out.print("快速排序");
                quickSort(0, data.length - 1);
                //quickSort(data, 0, data.length - 1);
                break;
        }
    }

    /**
     * 插入排序：稳定
     * 时间复杂度为O(N^2)，空间复杂度是O(1)
     */
    private static void insertionSort() {
        for (int i = 0; i < data.length; i++) { //外部循环控制待比较的数值
            for (int j = 0; j < data.length; j++) {   //内部循环比较数值确定其最终位置
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序：非稳定，比如5，4，5，2 -> 2，4，5，5把第一个5排在了后面。
     * 时间复杂度为O(N^2)，空间复杂度是O(1)
     */
    private static void selectionSort() {
        for (int i = 0; i < data.length - 1; i++) {  //做第i趟排序
            int k = i;
            for (int j = k + 1; j < data.length; j++) { //选最小的记录
                if (data[j] < data[k])
                    k = j;
            }
            //内循环结束，找到本轮循环最小的数后交换
            if (i != k)  //表示找到最小的值不是自身
            {
                int temp = data[i];
                data[i] = data[k];
                data[k] = temp;
            }
        }
    }

    /**
     * 冒泡排序：把小的向上浮，大的向下沉。稳定性
     */
    private static void bubbleSort() {
        for (int i = 0; i < data.length - 1; i++) {    //把大的往后排
            for (int j = 0; j < data.length - 1 - i; j++) {
                int a = data[j];
                int b = data[j + 1];
                if (a > b) {
                    data[j] = b;
                    data[j + 1] = a;
                }
            }
        }

      /*  for (int i = 0; i < data.length; i++) { //把小的向前排
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

    /**
     * 快速排序,二分法。不稳定
     * 平均时间复杂度为O(NlogN)
     * 注：必须是右边先开始找，如果从左边先开始找，当右边找不到小于基准数的时候，坐标与
     * 左边相等，则会置换基准数，而此时坐标上的数比基准数大。
     */
    private static void quickSort(int head, int last) {

        if (head > last) return;
        int i = head;
        int j = last;
        int key = data[head];
        while (i != j) {
            //退出while循环，表示已找到比基准数key小的数
            while (data[j] >= key && i < j) {
                j--;
            }
            //退出while循环，表示已找到比基准数key大的数
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
