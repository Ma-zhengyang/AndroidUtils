package com.android.mazhengyang.library.sort;

import java.util.Arrays;

/**
 * Created by mzy on 2018/12/24.
 */

public class CountingSort implements IArraySort {

//    计数排序
//
//算法步骤
//
//    花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max；
//    开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1)；
//    数组 B 中 index 的元素记录的值是 A 中某元素出现的次数；
//    最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数。

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxValue = getMaxValue(arr);

        return countingSort(arr, maxValue);
    }

    private int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];

        for (int value : arr) {
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

}
