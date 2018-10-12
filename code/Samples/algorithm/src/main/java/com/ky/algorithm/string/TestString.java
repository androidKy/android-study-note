package com.ky.algorithm.string;

import java.util.Arrays;

/**
 * description: 字符串操作
 * 参考链接：https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/
 * author: kyXiao
 * created date: 2018/9/30 10:16
 */

public class TestString {

    public static void main(String[] args) {
        rotateString();
    }

    /**
     * 旋转字符串
     */
    private static void rotateString() {
        String str = "abcdef";
        char[] srtChar = str.toCharArray();
        RotateStringObj rotateStringObj = new RotateStringObj();
        char[] data = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        rotateStringObj.leftShiftN(data, data.length, 4);
        System.out.println("leftMove 4 length data : " + Arrays.toString(data));

        data = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        //reverse(data, 1, 3);
        rotateStringObj.leftRotate(data, data.length, 4);   //dcba fe -> efabcd
        System.out.println("rotate data: " + Arrays.toString(data));
    }

    private static class RotateStringObj {
        /**
         * 向左移动1位字符到字符串的末尾
         *
         * @param s
         */
        private void leftShiftOne(char[] s, int n) {
            char first = s[0];

            for (int i = 1; i < n; i++) {
                s[i - 1] = s[i];
            }

            s[n - 1] = first;
        }

        /**
         * 暴力位移法：时间复杂度是O(mn),空间复杂度是O(1)
         * <p>
         * 向左移动m位字符到长度为n的字符串的末尾
         *
         * @param s
         * @param n
         * @param m
         */
        private void leftShiftN(char[] s, int n, int m) {
            while (m-- > 0) {
                leftShiftOne(s, n);
            }
        }

        /**
         * 反转字符串
         *
         * @param s
         * @param from
         * @param to
         */
        private void reverse(char[] s, int from, int to) {
            while (from < to) {
                char t = s[from];
                s[from++] = s[to];
                s[to--] = t;
            }
        }

        /**
         * 三步反转法：时间复杂度是O(n),空间复杂度是O(1)
         *
         * @param s
         * @param n
         * @param m
         */
        private void leftRotate(char[] s, int n, int m) {
            //m %= n;
            reverse(s, 0, m - 1);
            reverse(s, m, n - 1);
            reverse(s, 0, n - 1);
        }
    }

    /**
     * 回文判断:madam、我是我
     */
    private static void isPalindrome() {

    }

   /* private static class PalindromeStringObj {
        private void pointer(char[] s,) {

        }
    }*/
}
