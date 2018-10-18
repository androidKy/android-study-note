package com.ky.algorithm.sort;

/**
 * description: �����㷨
 * author: kyXiao
 * created date: 2018/9/28 11:50
 */

public class TestSort {
    private static final String TAG = "TestSort";
    //ʱ�临�Ӷȴ�->С:4-3-2-1
    private static final int INSERTION_SORT = 4;    //ʱ�临�Ӷ�ΪO(N^2)���ռ临�Ӷ���O(1)
    private static final int SELECTION_SORT = 3;    //ʱ�临�Ӷ�ΪO(N^2)
    private static final int BUBBLE_SORT = 2;   //
    private static final int QUICK_SORT = 1;    //ƽ��ʱ�临�Ӷ�ΪO(NlogN)

    private static int[] data = new int[]{2, 1, 4, 5, 10, 9, 8, 7, 1, 11, 6, 0};

    private static int SORT_TYPE = SELECTION_SORT;

    public static void main(String[] args) {

        for (int i = 1; i < 5; i++) {
            System.out.println("");
            System.out.println("����ǰ��");
            printResult();
            System.out.println("");
            sort(i);
            System.out.println("��");
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
                System.out.print("��������");
                insertionSort();
                break;
            case SELECTION_SORT:
                System.out.print("ѡ������");
                selectionSort();
                break;
            case BUBBLE_SORT:
                System.out.print("ð������");
                bubbleSort();
                break;
            case QUICK_SORT:
                System.out.print("��������");
                quickSort(0, data.length - 1);
                //quickSort(data, 0, data.length - 1);
                break;
        }
    }

    /**
     * ���������ȶ�
     * ʱ�临�Ӷ�ΪO(N^2)���ռ临�Ӷ���O(1)
     */
    private static void insertionSort() {
        for (int i = 0; i < data.length; i++) { //�ⲿѭ�����ƴ��Ƚϵ���ֵ
            for (int j = 0; j < data.length; j++) {   //�ڲ�ѭ���Ƚ���ֵȷ��������λ��
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * ѡ�����򣺷��ȶ�������5��4��5��2 -> 2��4��5��5�ѵ�һ��5�����˺��档
     * ʱ�临�Ӷ�ΪO(N^2)���ռ临�Ӷ���O(1)
     */
    private static void selectionSort() {
        for (int i = 0; i < data.length - 1; i++) {  //����i������
            int k = i;
            for (int j = k + 1; j < data.length; j++) { //ѡ��С�ļ�¼
                if (data[j] < data[k])
                    k = j;
            }
            //��ѭ���������ҵ�����ѭ����С�����󽻻�
            if (i != k)  //��ʾ�ҵ���С��ֵ��������
            {
                int temp = data[i];
                data[i] = data[k];
                data[k] = temp;
            }
        }
    }

    /**
     * ð�����򣺰�С�����ϸ���������³����ȶ���
     */
    private static void bubbleSort() {
        for (int i = 0; i < data.length - 1; i++) {    //�Ѵ��������
            for (int j = 0; j < data.length - 1 - i; j++) {
                int a = data[j];
                int b = data[j + 1];
                if (a > b) {
                    data[j] = b;
                    data[j + 1] = a;
                }
            }
        }

      /*  for (int i = 0; i < data.length; i++) { //��С����ǰ��
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
     * ��������,���ַ������ȶ�
     * ƽ��ʱ�临�Ӷ�ΪO(NlogN)
     * ע���������ұ��ȿ�ʼ�ң����������ȿ�ʼ�ң����ұ��Ҳ���С�ڻ�׼����ʱ��������
     * �����ȣ�����û���׼��������ʱ�����ϵ����Ȼ�׼����
     */
    private static void quickSort(int head, int last) {

        if (head > last) return;
        int i = head;
        int j = last;
        int key = data[head];
        while (i != j) {
            //�˳�whileѭ������ʾ���ҵ��Ȼ�׼��keyС����
            while (data[j] >= key && i < j) {
                j--;
            }
            //�˳�whileѭ������ʾ���ҵ��Ȼ�׼��key�����
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
