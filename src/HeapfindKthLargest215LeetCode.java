import java.util.PriorityQueue;
/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k
 * 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * @author LemonLin
 * @Description :HeapfindKthLargest215LeetCode
 * @date 20.2.3-16:02
 * 思路：利用最小堆（堆顶元素比其他元素小）处理问题。遍历数组，往最小堆里存放数，
 * 如果堆元素个数小于K，继续存入，知道堆元素个数等于k,此时比较从数组中读取的元素和
 * 最小堆堆顶元素：
 *      如果数组元素比堆顶元素大，替换堆顶元素，堆重新排序；
 *      如果数组元素比堆顶元素小，数组下标下移一个。
 *记忆点1：注意这里看到题目第K最大的元素，容易思维定势认为是用脑最大堆，这里总结一下：
 * 最大堆(大顶堆)，用来求第K个最小的元素。
 * 最小堆(小顶堆)，用来求第K个最大的元素。
 * 解释一下：因为最大堆是堆顶节点比堆中其他元素大，所以堆中其他元素可以更小，而堆
 * 顶元素通过控制堆的大小为K来控制是第K小，堆中其他元素合起来是K-1小，那么堆顶元素
 * 就是第K小。这个容易想混。
 * 记忆点2：优先队列默认情况下(没有比较器修改)是最小堆，最小堆求的是第K大。
 * 记忆点3：堆的一些实用api:
 *      minHeap.size() 堆中元素实际有多少个
*       peek()           获取但不移除此队列的头；如果此队列为空，则返回 null。
 *      poll()           获取并移除此队列的头，如果此队列为空，则返回 null。
 *      offer()          插入元素堆自动调整
 */
public class HeapfindKthLargest215LeetCode {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length<k)return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //临时存放堆顶元素
        for (int i=0;i<nums.length;i++){
            if (minHeap.size()<k){
                minHeap.offer(nums[i]);
                continue;
            }
            //peek()           获取但不移除此队列的头；如果此队列为空，则返回 null。
            // poll()           获取并移除此队列的头，如果此队列为空，则返回 null。
            if (nums[i]>minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums ={3,2,3,1,2,4,5,5,6};
        int k=4;
        System.out.println(new HeapfindKthLargest215LeetCode().
                findKthLargest(nums, k));
    }
}
