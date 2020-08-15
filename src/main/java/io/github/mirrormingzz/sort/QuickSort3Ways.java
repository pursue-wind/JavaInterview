package io.github.mirrormingzz.sort;

import java.util.Arrays;

/**
 * 三路快速排序
 *
 * @author Mireal
 */
public class QuickSort3Ways {
    static void quickSort3Ways(int[] arr) {
        quickSort3Ways(arr, 0, arr.length - 1);
    }

    private static void quickSort3Ways(int[] arr, int l, int r) {
        if (l >= r) return;

        //arr[l ... lt] < v
        int lt = l;
        //arr[gt ... r] > v
        int gt = r + 1;
        // arr[lt ... i) ==v
        int i = l + 1;

        int v = arr[l];
        while (i < gt) {
            if (arr[i] < v)
                swap(arr, i++, ++lt);
            else if (arr[i] > v)
                swap(arr, --gt, i);
            else //if (arr[i] == v)
                i++;
        }

        swap(arr, l, lt);
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, gt, r);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 2, 1, 9, 5, 7, 4};
//        quickSort3Ways(arr);
        quickSort3WaysWithPrint(arr);
        System.out.println(Arrays.toString(arr));
    }


    //~ 下面是带打印的版本
    //==========================================================================================================================================

    static void quickSort3WaysWithPrint(int[] arr) {
        quickSort3WaysWithPrint(arr, 0, arr.length - 1);
    }

    private static void quickSort3WaysWithPrint(int[] arr, int l, int r) {
        if (l >= r) return;

        //arr[l ... lt] < v
        int lt = l;
        //arr[gt ... r] > v
        int gt = r + 1;
        // arr[lt ... i) ==v
        int i = l + 1;

        int v = arr[l];
        while (i < gt) {
            if (arr[i] < v) {
                printIPos(i, arr, lt, gt);
                System.err.println(arr[i] + "小于" + v + "     交换 " + arr[i] + " <--> " + arr[(lt + 1)]);
                swap(arr, i++, ++lt);
                printIPos(i, arr, lt, gt);
            } else if (arr[i] > v) {
                printIPos(i, arr, lt, gt);
                System.err.println(arr[i] + " 大于 " + v + "    交换 " + arr[i] + " <--> " + arr[(gt - 1)]);
                swap(arr, --gt, i);
                printIPos(i, arr, lt, gt);
            } else {
                //if (arr[i] == v)
                i++;
            }
        }

        swap(arr, l, lt);
        quickSort3WaysWithPrint(arr, l, lt - 1);
        quickSort3WaysWithPrint(arr, gt, r);
    }

    /**
     * 打印 指针位置
     */
    private static void printIPos(int i, int[] arr, int lt, int gt) {
        System.err.println("============================================");
        System.err.print("i 位置： ");
        for (int j = 0; j < i; j++) {
            System.err.print("   ");
        }
        System.err.print("⬇");
        System.err.println();
        System.err.println("  数组：" + Arrays.toString(arr));
        System.err.print("lt位置： ");
        for (int j = 0; j < lt; j++) {
            System.err.print("   ");
        }
        System.err.print("⬆");
        System.err.println();
        System.err.print("gt位置： ");
        for (int j = 0; j < gt; j++) {
            System.err.print("   ");
        }
        System.err.print("⬆");
        System.err.println();
        System.err.println("============================================");
    }
}
