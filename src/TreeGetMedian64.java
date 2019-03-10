import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author LemonLin
 * @Description :TreeGetMedian64
 * @date 2019/3/10-10:39
 *题目描述
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间
 * 的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们
 * 使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数
 *
 * 这里的中位数和数据中的有差别，如果是总数目是偶数个，去排好序之后的中间两个数的和的二分之一
 *
 *
 * 解题思路：
 * 数据流中的数据用数据容器存储，可以分成两部分：位于容器左边部分的数据比右边的数据小，用一个最大堆实现
 * 左边的数据容器，用最小堆实现右边的数据容器。往堆中插入一个数据的时间复杂度O(logn),得到堆顶的数据是O(1)
 *
 * 实现细节：保证数据平均分配到两个堆中，因此两个堆中的数据数目之差不能超过1.可以在数据的总数目是偶数时
 * 把新数据插入到最小堆中，否则插入到最大堆中；
 * 这里解释一下最大堆，堆顶元素最大，其余最小，容易想错；最小堆，堆顶元素最小，其余最大；
 *
 * 当数据的总数目是偶数时，数据插入最小堆中，为了保证新的数据比最大堆中的数据要小，可以先把新数据插入到
 * 最大堆中，接着把最大堆中的最大的数拿出来插入到最小堆中。
 *
 * 小知识点：Java中PriorityQueue的理解https://www.cnblogs.com/CarpenterLee/p/5488070.html
 *      PriorityQueue(优先队列):优先队列的作用是能保证每次取出的元素都是队列中权值最小的.PriorityQueue是
 * 容量自动增长的。
 *      add(E e)和offer(E e)的语义相同，都是向优先队列中插入元素，只是Queue接口规定二者对插入失败时的处理
 * 不同，前者在插入失败时抛出异常，后则则会返回false
 *      remove()和poll()方法的语义也完全相同，都是获取并删除队首元素，区别是当方法失败时前者抛出异常，后者
 * 返回null
 *
 */
public class TreeGetMedian64 {

    private  int count  = 0;
    //默认是最小堆，重写compare可以实现最大堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer o1,Integer o2){
            return o2-o1;
        }
    });

    public void Insert(Integer num) {
            if (count%2==0){
                maxHeap.offer(num);
                Integer filteredMaxNum = maxHeap.poll();
                minHeap.offer(filteredMaxNum);
            }
            else {
                minHeap.offer(num);
                Integer filteredMinNum = minHeap.poll();
                maxHeap.offer(filteredMinNum);
            }
            count++;
    }

    public Double GetMedian() {
        if (count%2==0){
            return new Double((minHeap.peek()+maxHeap.peek()))/2;
        }else {
            return new Double(minHeap.peek());
        }
    }

    public static void main(String[] args) {
        TreeGetMedian64 treeGetMedian64= new TreeGetMedian64();
        treeGetMedian64.Insert(1);
        treeGetMedian64.Insert(2);
        treeGetMedian64.Insert(3);
        treeGetMedian64.Insert(4);
        Double result = treeGetMedian64.GetMedian();
        System.out.println(result);
    }
}
