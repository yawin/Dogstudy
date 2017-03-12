package com.powerzhou.arithmetic.sort;

import java.util.ArrayList;
import java.util.List;

/**基数排序(只针对正整数，对负整数只需要把负号去掉后排序再逆序)
 * 基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort，
 * 顾名思义，它是透过键值的部份资讯，将要排序的元素分配至某些“桶”中，藉以达到排序的作用，基数排序法是属于稳定性的排序，
 * 其时间复杂度为O (nlog(r)m)，其中r为所采取的基数，而m为堆数，在某些时候，基数排序法的效率高于其它的稳定性排序法。
 * Created by Administrator on 2017/3/12 0012.
 */

public class RadixSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        //数字的最大长度，即位数
        int maxLength = 1;
        //获取最大长度，通过拿到最大数得到
        int temp = 0;
        for(int i=0;i<length;i++){
            if(temp < sourceArray[i]){
                temp = sourceArray[i];
            }
        }
        while(temp != 0){
            temp = temp/10;
            maxLength ++;
        }
        ArrayList<List<Integer>> baseIndexList = new ArrayList<>();
        for(int i=0;i<10;i++){
            baseIndexList.add(new ArrayList<Integer>());
        }

        for(int i=0;i<maxLength;i++){
            //遍历数组，把i（0表示个位0,1表示十位，2表示百位，以此类推）
            for(int j=0;j<length;j++){
                //在此位的数组值，i=0表示个位的值，值为1 则放入到baseIndexList下标为1的List中
                //先求余再除，对结果取整
                int x = sourceArray[j]%(int)Math.pow(10,i+1)/(int)Math.pow(10,i);
                baseIndexList.get(x).add(sourceArray[j]);
            }
            //对sourceArray按照i位排序
            int index = 0;
            for(int k=0;k<baseIndexList.size();k++){
                int listSize = baseIndexList.get(k).size();
                if(listSize == 0){
                    continue;
                }
                while(baseIndexList.get(k).size()>0){
                    sourceArray[index++] =  baseIndexList.get(k).get(0);
                    //获取到数据后就移除掉，方便下个位数用
                    baseIndexList.get(k).remove(0);
                }
            }
        }
    }
}
