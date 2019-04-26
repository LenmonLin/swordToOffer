/**
 * @author LemonLin
 * @Description :QuickSort
 * @date 2018/3/24-15:33
 *
 * 快速排序算法是对冒泡排序的一种改进，其基本思想是基于分治法，在待排序的N个元素中任取一个元素pivot
 * 作为基准，通过一趟排序将待排序表划分为独立的两部分L[1...k-1] 和L[k+1...n],使得L[1...k-1]中所有元素小于
 * pivot,L[k+1...n]中所有的元素大于pivot，则基准元素放在了其最终位置L(k)上，这个过程称为一趟快速排序。
 * 而后分别递归地对两个子表重复上述过程，直至每部分内只有一个元素或空为止，即所有元素放在了其最终位置上。
 *
 * 快速排序提出至今，有许多不同的划分操作版本，笔者写的是假设每次总是以当前表中第一个元素作为枢纽值（基准）
 * 对表进行划分。
 *
 * 空间效率：由于快速排序是递归的，需要借助一个递归工作栈来保存每一层递归调用的必要信息，其容量应与
 * 递归调用的最大深度一致。最好情况下为log2(n+1),最坏情况下，因为要进行n-1次递归调用，所以栈的深度为
 * O(n).平均情况下，栈的深度为O（log2(n)）
 *
 * 时间效率：快速排序的运行时间与划分是否堆成有关，最坏情况发生在两个区域分别包含n-1 个元素和0 个元素，这种
 * 最大程度的不对称若发生在每一层递归上，即对应于初始排序表基本有序或基本逆序，就得到最坏时间复杂度O(n2)
 *
 * 平均情况下O(nlog2n),快速排序是所有排序算法中平均性能最优的排序算法。
 *
 * 稳定性：划分算法中，若右端区间存在两个关键字相同，且均小于基准值得记录，则在交换到左端区间后，它们
 * 的相对位置会发生变化，即快排是个不稳定的排序方法
 */
public class SortQuickSortByLemonLin {


    public void sort(int arr[],int low,int high){
        int l=low;
        int h=high;
        //这个一定要加，否则l+1成为越界数组
        if (l<h) {
            //这里的temp也可以设置成全局变量
            int temp = arr[l];

            while (l < h) {

                //第一个先选用的是低位的数组为参考对象，则从右边比较起来
                while (l < h && temp <= arr[h]) h--;
                if (l < h) {
                    arr[l] = arr[h];
                    //交换之后，l向后移动一位
                    l++;
                }

                while (l < h && arr[l] <= temp) l++;

                if (l < h) {
                    arr[h] = arr[l];
                    //交换之后，h向前移动一位
                    h--;
                }
            }

            arr[l] = temp;
            //以上完成了第一趟的排序

            //递归对temp左边的元素进行排序,l<=h一定要加，否则一直循环无法退出
            if (l <= h) sort(arr, low, l - 1);
            //递归对temp右边的元素进行排序,l<=h一定要加，否则一直循环无法退出
            if (l <= h) sort(arr, l + 1, high);
        }
    }




    //测试代码
    public static void main(String[] args) {
        SortQuickSortByLemonLin quickSortByLemonLin = new SortQuickSortByLemonLin();

        int array [] = {49,38,65,97,76,13,27,49};
        quickSortByLemonLin.sort(array,0,array.length-1);

        for (int i=0 ;i<array.length;++i){
            System.out.println(array[i]);
        }
    }
}
