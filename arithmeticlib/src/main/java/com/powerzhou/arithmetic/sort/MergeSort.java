package com.powerzhou.arithmetic.sort;

/**
 * 归并排序
 * 归并排序（MERGE-SORT）是建立在归并操作上的一种有效的排序算法,该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 * 归并过程为：比较a[i]和a[j]的大小，若a[i]≤a[j]，则将第一个有序表中的元素a[i]复制到r[k]中，并令i和k分别加上1；
 * 否则将第二个有序表中的元素a[j]复制到r[k]中，并令j和k分别加上1，如此循环下去，直到其中一个有序表取完，
 * 然后再将另一个有序表中剩余的元素复制到r中从下标k到下标t的单元。
 * 归并排序的算法我们通常用递归实现，先把待排序区间[s,t]以中点二分，接着把左边子区间排序，再把右边子区间排序，
 * 最后把左区间和右区间用一次归并操作合并成有序的区间[s,t]。
 * 平均时间O(nlogn) 最差情形O(nlogn) ， 稳定排序，额外空间O(1) ,n 较大时比较好
 * Created by Administrator on 2017/3/12 0012.
 */

public class MergeSort extends DataSort {
    @Override
    public void dataSort() {
        mergeSort(sourceArray,0,sourceArray.length-1);
    }

    private void mergeSort(int[] array,int left,int right){
        if(left < right){
            int middle = (left+right)/2;
            mergeSort(array,left,middle);
            mergeSort(array,middle+1,right);
            mergeData(array,left,middle,right);
        }
    }

    /**
     * 核心算法
     * 因为采用递归，所以先是从左右两个元素比较起，然后再层层返回进行合并，可以知道left到middle和middle到right的数据是有序的
     * @param array
     * @param left
     * @param middle
     * @param right
     */
    private void mergeData(int[] array,int left, int middle, int right){
        //定义一个中间数组，用来存放合并后的元素
        int[] tempArray = new int[right - left + 1];
        int rightStart = middle + 1;
        int tempLeft = left;
        int tempIndex = 0;
        //首先比较左右元素，先放小的进中间数组
        while(left<=middle && rightStart<=right){
            if(array[left]<=array[rightStart]){
                tempArray[tempIndex++] = array[left++];
            }else{
                tempArray[tempIndex++] = array[rightStart++];
            }
        }
        //如果左边还有数据
        while(left<=middle){
            tempArray[tempIndex++] = array[left++];
        }
        //如果右边还有数据
        while(rightStart<=right){
            tempArray[tempIndex++] = array[rightStart++];
        }
        //将tempArray数据复制到array
        for(int i=0;i<tempArray.length;i++){
            array[tempLeft+i] = tempArray[i];
        }
    }
}
