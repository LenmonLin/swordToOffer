import java.util.HashMap;
import java.util.LinkedList;
/**
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到
 *  nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大
 * 的元素。如果不存在，对应位置输出-1。
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 注意:
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 * @author LemonLin
 * @Description :StacknextGreaterElement496LeetCode
 * @date 20.3.30-20:33
 * 思路：https://leetcode-cn.com/problems/next-greater-element-i/solution/
 * dan-diao-zhan-jie-jue-next-greater-number-yi-lei-w/
 * 暴力算法，时间复杂度为O(n2)
 * 单调栈解法：O(n)
 * 本题难点：有两个输入数组，但是求得是nums2的单点栈，这里需要用一个hashmap来
 * 记录nums2以及它的下一个较大的数字，然后求nums1的话，直接通过hashMap的key
 * 寻找即可。
 * 单调栈的标准套路写法：
 * for(从后往前遍历){
 *     while(){
 *         如果栈顶元素比nums[i]小，不断出栈
 *     }
 *     这个时候栈顶元素一定比nums[i]大，所以nums[i]入栈
 * }
 */
public class StacknextGreaterElement496LeetCode {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        LinkedList <Integer> stack = new LinkedList<>();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        //单调栈是从后往前遍历的
        //这个是单调栈的标准套路写法
        for (int i=nums2.length-1;i>=0;i--){
            //这里设置链表的头为栈顶元素,主要这里要用<=,为啥有等号，因为while循环之后，
            //nums2[i]一定要入栈，如果等于nums2[i]时，不进入while循环出栈的话，栈中
            // 就会有重复元素。这样，求栈顶元素的时候就会出错。
            while (!stack.isEmpty()&&stack.peekFirst()<=nums2[i]){
                stack.removeFirst();
            }
            hashMap.put(nums2[i] , stack.isEmpty()?-1:stack.peekFirst());
            stack.addFirst(nums2[i]);
        }
        for (int i=0;i<nums1.length;i++){
            nums1[i]=hashMap.get(nums1[i]);
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1={4,1,2};
        int[] nums2={1,3,4,2};
        int[] result = new StacknextGreaterElement496LeetCode().nextGreaterElement(nums1, nums2);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}
