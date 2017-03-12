package com.powerzhou.arithmetic.sort;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public abstract class DataSort {
    /**
     * 源数组，用来排序
     */
    protected int[] sourceArray = new int[]{20,12,3,5974,1,2,43,12,0,8,89,1235,235,66,897,586};

    /**
     * sort the data
     */
   public abstract void dataSort();

    /**
     * 交换数组下标i 与 下标 j的值
     * @param desArray
     * @param i
     * @param j
     */
    public void swap(int[] desArray , int i , int j){
        if(desArray == null || i < 0 || j < 0 || i >= desArray.length || j >= desArray.length || i==j){
            //do nothing
            return;
        }

        desArray[i] ^= desArray[j];
        desArray[j] ^= desArray[i];
        desArray[i] ^= desArray[j];
    }

    /**
     * 打印array 数组
     * @param array
     * @return
     */
    public String arrayToString(int[] array){
        if(array == null ){
            return null;
        }
        if(array.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = array.length;
        for (int i=0;i<length;i++){
            if(i == length-1){
                sb.append(array[i]);
            }else{
                sb.append(array[i]).append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int[] getSourceArray() {
        return sourceArray;
    }

    public void setSourceArray(int[] sourceArray) {
        this.sourceArray = sourceArray;
    }
}
