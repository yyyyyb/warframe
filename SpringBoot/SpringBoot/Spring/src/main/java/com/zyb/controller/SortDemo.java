package com.zyb.controller;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {
        int a[] = {8, 4, 2, 0, 7, 10, 85, 14, 25, 19};
        int b[] = {8, 4, 2, 0, 7, 10, 85, 14, 25, 19};
        int c[] = {8, 4, 2, 0, 7, 10, 85, 14, 25, 19};
        /**
         * 冒泡排序
         */
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        /**
         * 选择排序
         */

        for (int i = 0; i < b.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < b.length; j++) {
                if (b[minIdx] > b[j]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                int temp = b[i];
                b[i] = b[minIdx];
                b[minIdx] = temp;
            }
        }

        /**
         * 插入排序
         */
        for (int i = 1; i < c.length; i++) {
            int temp = c[i];
            int j = i;
            while (j > 0 && temp < c[j - 1]) {
                c[j] = c[j - 1];
                j--;
            }
            if (j != i) {
                c[j] = temp;
            }
        }


        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }


}
