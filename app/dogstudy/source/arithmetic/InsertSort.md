```
/**
 * 直接插入排序
 * 插入排序是在一个已经有序的小序列的基础上，一次插入一个元素。
 * 当然，刚开始这个有序的小序列只有1个元素，就是第一个元素。
 * 比较是从有序序列的末尾开 始，也就是想要插入的元素和已经有序的最大者开始比起，如果比它大则直接插入在其后面，否则一直往前找直到找到它该插入的位置。
 * 如果碰见一个和插入元素相 等的，那么插入元素把想插入的元素放在相等元素的后面
 * 每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插入完毕。
 * 直接插入排序是固定元素，找位置
 * 平均时间O(n^2) 最差情形O(n^2) ， 稳定排序，额外空间O(1)大部分已排好序时较好
 * Created by Administrator on 2017/3/12 0012.
 */

public class InsertSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        for(int i=1;i<length;i++){
            //待插入元素
            int temp = sourceArray[i];
            int j ;
            for(j=i-1;j>=0;j--){
                if(sourceArray[j]>temp){
                    sourceArray[j+1] = sourceArray[j];
                }else{
                    break;
                }
            }
            sourceArray[j+1] = temp;
        }
    }
}
```