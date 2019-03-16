import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * @author LemonLin
 * @Description :KLeastNumbers30
 * @date 2018/4/12-16:20
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 解法一：O(n)的算法，只有当我们可以修改输入的数组时可用
 * 可以基于数组的第k个数字来调整，使得比第k个数字小的所有数字都位于数组的左边，比第k个数字大的所有
 * 数字都位于数组的右边
 *解法二：O(nlogk)的算法，适合处理海量的数据
 * 算法思想就是利用大顶堆保存最小的四个数
 * 如果没有存满，就继续存入，如果存满了，就和堆顶元素进行比较，如果堆顶元素小的话，不处理
 * 如果堆顶元素大的话，就把堆顶删除，并且入堆进行调整。
 * 最后输出堆中的元素即可
 *
 * 难点在于怎么建立大顶堆，Java中这个PriorityQueue是小顶堆，可以以下操作，就变成大顶堆
 * private static final int DEFAULT_INITIAL_CAPACITY = 11;
    PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY, new Comparator<Integer>() {
        @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
    });
 */
public class HeapKLeastNumbers30 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        //牛客网返回参数为arrayList,所以需要新建一个arrayList来存储数据；
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        //判断非法的输入
        if(input.length<k||k==0){
            return arrayList;
        }
        //创建一个大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(4, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i=0;i<input.length;++i){
            if (maxHeap.size()<k){
                maxHeap.add(input[i]);
            }else if (maxHeap.peek()>input[i]){
                maxHeap.remove();
                maxHeap.add(input[i]);
            }
        }
        //把堆中的已经是最小的k个元素放入到arrayList中去；
        //注意因为这里maxHeap中有迭代器，所以可以使用下面的写法
        for (Integer integer:maxHeap){
            arrayList.add(integer);
        }
        return arrayList;
    }
    public static void main(String[] args) {
        int [] array={4,5,1,6,2,7,3,8};
        HeapKLeastNumbers30 heapKLeastNumbers30 = new HeapKLeastNumbers30();
        ArrayList<Integer> arrayList = heapKLeastNumbers30.GetLeastNumbers_Solution(array, 4);
        for(Integer integer : arrayList){
            System.out.println(integer);
        }
    }
}