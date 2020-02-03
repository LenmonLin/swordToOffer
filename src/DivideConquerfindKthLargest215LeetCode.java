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
 * @Description :DivideConquerfindKthLargest215LeetCode
 * @date 20.2.3-16:02
 * 思路：利用最小堆（堆顶元素比其他元素小）处理问题。遍历数组，往最小堆里存放数，
 * 如果堆元素个数小于K，继续存入，知道堆元素个数等于k,此时比较从数组中读取的元素和
 * 最小堆堆顶元素：
 *      如果数组元素比堆顶元素大，替换堆顶元素，堆重新排序；
 *      如果数组元素比堆顶元素小，数组下标下移一个。
 *
 */
public class DivideConquerfindKthLargest215LeetCode {
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
        System.out.println(new DivideConquerfindKthLargest215LeetCode().
                findKthLargest(nums, k));
    }
}
