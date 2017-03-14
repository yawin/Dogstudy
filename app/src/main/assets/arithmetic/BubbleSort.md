```
/**
 *
 *  冒泡排序
 *  冒泡排序算法的运作如下:
     比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     针对所有的元素重复以上的步骤，除了最后一个。
     持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。

 *  平均时间O(n^2) 最差情形O(n^2) ， 稳定排序，额外空间O(1) n小时较好
 *
 *  注：排序的稳定性是指如果在排序的序列中，存在前后相同的两个元素的话，排序前 和排序后他们的相对位置不发生变化
 * Created by Administrator on 2017/3/12 0012.
 */

public class BubbleSort extends DataSort{

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-i-1;j++){
                if(sourceArray[j]>sourceArray[j+1]){
                    swap(sourceArray,j,j+1);
                }
            }
        }
    }
}
```