package com.powerzhou.arithmetic.sort;

/**希尔排序
 *  属于插入类排序,是将整个有序序列分割成若干小的子序列分别进行插入排序。
 *  平均时间O(nlogn) 最差情形O(n^s 1<s<2) ， 不稳定排序，额外空间O(1) , s为所选分组
 * Created by Administrator on 2017/3/12 0012.
 */

public class ShellSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        //增量
        int addUp = length;
        while(true){
            addUp = addUp/2;
            for(int i=0;i<addUp;i++){
                //下面的排序也可以选择其他的排序方式，这里采用插入排序，因为当增量越来越小时，数组的有序越来越好
                //进行插入排序，可以参考插入排序算法，需要注意的是这里的增量是addUp，插入排序的增量一般是1
                for(int j=i+addUp;j<length;j+=addUp){
                    int temp = sourceArray[j];
                    int k;
                    for(k=j-addUp;k>=0;k-=addUp){
                        if(temp<sourceArray[k]){
                            sourceArray[k+addUp] = sourceArray[k];
                        }else{
                            break;
                        }
                    }
                    sourceArray[k+addUp] = temp;
                }
            }
            if(addUp == 1){
                break;
            }
        }
    }
}
