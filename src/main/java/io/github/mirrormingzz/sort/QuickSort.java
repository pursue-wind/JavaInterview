package io.github.mirrormingzz.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Mireal
 */
public class QuickSort {
    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r)
            return;

        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++)
            if (arr[i] < v)
                swap(arr, ++j, i);

        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 6, 2, 3, 1, 5, 7, 4};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
