package com.powerzhou.arithmetic.sort;

/**
 * 堆排序
 * 堆排序(Heapsort)是指利用堆积树（堆）这种数据结构所设计的一种排序算法，它是选择排序的一种。
 * 可以利用数组的特点快速定位指定索引的元素。堆分为大根堆和小根堆，是完全二叉树。
 * 大根堆的要求是每个节点的值都不大于其父节点的值，即A[PARENT[i]] >= A[i]。
 * 在数组的非降序排序中，需要使用的就是大根堆，因为根据大根堆的要求可知，最大的值一定在堆顶
 * 平均时间O(nlogn) 最差情形O(nlogn) ， 不稳定排序，额外空间O(1) ,n大时较好
 * Created by Administrator on 2017/3/12 0012.
 */

public class HeapSort extends DataSort {
    @Override
    public void dataSort() {
        buildLatestHeap(sourceArray);
        headSort(sourceArray);
    }

    /**
     * 排序，把堆顶元素一个个拿出来
     */
    private void headSort(int[] array){
        //因为创建最大堆后最大数值处于堆顶，需要交换到最后一个位置
        for (int i = array.length-1; i > 0 ; i--) {
            swap(array,i,0);
            //因为已经是最大堆，同时堆顶已经被替换，所以只要重新再处理一次堆顶，即index 0 位置即可
            maxHeap(array,i,0);
        }
    }

    /**
     * 创建最大堆
     * @param array
     */
    private void buildLatestHeap(int[] array){
        if(array == null || array.length == 0){
            return;
        }
        //按照满二叉树的机构，我们只需要从length/2处开始遍历
        int maxLength = array.length/2;
        for(int i=maxLength;i>=0;i--){
            maxHeap(array,array.length,i);
        }
    }



    /**
     * 创建堆
     * @param array 数组
     * @param length 堆的长度
     * @param i 父节点
     */
    private void maxHeap(int[] array,int length,int i){
        int leftNode = 2*i+1;
        int rightNode = 2*i +2;
        int latestIndex = i;
        if(leftNode<length && array[leftNode]>array[latestIndex]){
            latestIndex = leftNode;
        }
        if(rightNode<length && array[rightNode]>array[latestIndex]){
            latestIndex = rightNode;
        }
        //如果i与latestIndex不等，表示有子节点比根结点大，需要交换
        if(i != latestIndex){
            swap(array,i,latestIndex);
            //继续对交换后的子节点进行建堆
            maxHeap(array,length,latestIndex);
        }
    }
}
