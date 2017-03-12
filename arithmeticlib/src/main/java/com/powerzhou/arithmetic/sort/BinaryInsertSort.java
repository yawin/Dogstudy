package com.powerzhou.arithmetic.sort;

/**
 * 二分插入排序
 * 二分法插入排序是在插入第i个元素时，对前面的0～i-1元素进行折半，先跟他们中间的那个元素比，如果小，则对前半再进行折半，否则对后半进行折半，
 * 直到left>right，然后再把第i个元素前1位与目标位置之间的所有元素后移，再把第i个元素放在目标位置上。
 * 从下标0开始循环处理，这样第i个元素之前的数据都是已经排好序的，在一个排好序的数组中通过二分查找法查找到比i元素的位置，然后把该位置开始的全部数据后移一位，再插入i的元素值
 * 平均时间O(n^2) 最差情形O(n^2) ， 稳定排序，额外空间O(1) 大部分已排好序时较好,优于插入排序
 * Created by Administrator on 2017/3/12 0012.
 */

public class BinaryInsertSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        for (int i=0;i<length;i++){
            int temp = sourceArray[i];
            int left = 0;
            int right = i-1;
            int mid;
            //通过二分查找到位置，为什么可以用二分查找，是因为i位置前面所有的数据都已经排好序(从下标0开始排序的)
            while(left <= right){
                mid = (left+right)/2;
                if(temp < sourceArray[mid]){
                    right = mid - 1;
                }else{
                    //这里同时处理等于的情况，因为是按照left下标来后移的
                    left = mid + 1;
                }
            }
            //把left之后包括left的元素后移一位
            for(int j = i-1;j>=left;j--){
                sourceArray[j+1] = sourceArray[j];
            }
            //left == i 表示该元素不用移动
            if(left != i){
                sourceArray[left] = temp;
            }
        }
    }

}
