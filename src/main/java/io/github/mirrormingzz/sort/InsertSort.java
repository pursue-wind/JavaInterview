package io.github.mirrormingzz.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Mireal
 */
public class InsertSort {
    /**
     * 插入排序 1
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--)
                swap(arr, j, j - 1);

    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * 插入排序 2
     * 优化方案： 交换 -> 赋值
     */
    public static void betterInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0 && temp < arr[j - 1]; j--)
                arr[j] = arr[j - 1];
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = {9, 8, 6, 2, 3, 1, 5, 7, 4};
//        insertSort(arr);
        betterInsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
