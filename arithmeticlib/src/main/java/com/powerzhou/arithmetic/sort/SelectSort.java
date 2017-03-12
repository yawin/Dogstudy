package com.powerzhou.arithmetic.sort;

/**
 * 选择排序
 * 从所有序列中先找到最小的，然后放到第一个位置。之后再看剩余元素中最小的，放到第二个位置……以此类推，就可以完成整个的排序工作
 * 选择排序是固定位置，找元素
 * 平均时间O(n^2) 最差情形O(n^2) ， 不稳定排序，额外空间O(1) n小时较好
 * Created by Administrator on 2017/3/12 0012.
 */

public class SelectSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;

        for(int i=0;i<length;i++){
            int min = sourceArray[i];
            int index = i;
            for(int j=i+1;j<length;j++){
                if(sourceArray[j]<min){
                    min = sourceArray[j];
                    index = j;
                }
            }
            swap(sourceArray,i,index);
        }
    }
}
