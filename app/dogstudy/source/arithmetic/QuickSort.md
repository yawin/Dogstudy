```
/**
 * 快速排序 ， 冒泡排序的改进
 * 快速排序由C. A. R. Hoare在1962年提出。它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * 平均时间O(nlogn) 最差情形O(n2) ， 不稳定排序，额外空间O(nlogn) , n 较大时较好
 * Created by Administrator on 2017/3/12 0012.
 */

public class QuickSort extends DataSort {

    @Override
    public void dataSort() {
        int length = sourceArray.length;
        // 起始把下标为0 的数值当为基准数
        quickSort(sourceArray,0,length-1);
    }

    private void quickSort(int[] array,int low , int high){
        if(low<high){
            int middle = getMiddle(array,low,high);
            quickSort(array,low,middle);
            quickSort(array,middle+1,high);
        }
    }

    /**
     * 获取基准数的在排好序的数组中的下标,同时会把大于基准数的数据放到右边，小于基准数的数据放到左边
     * @param array
     * @param low
     * @param high
     * @return
     */
    private int getMiddle(int[] array,int low,int high){
        int temp = array[low];
        while(low<high) {
            while (low < high && temp <= array[high]) {
                high--;
            }
            //需要把小于基准数的数据放到基准数的左边
            array[low] = array[high];
            while (low < high && temp >= array[low]) {
                low++;
            }
            //需要把大于基准数的数据放到基准数的右边
            array[high] = array[low];
        }
        //当low>=high时，表示所有小于基准数的数据都被放到了基准数的左边，所有大于基准数的数据都被放到了基准数的右边
        //此时low的数据就是该基准数在排好序数组中的位置
        array[low]= temp;
        return low;
    }

}
```