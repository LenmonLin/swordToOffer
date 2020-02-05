/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 * @author LemonLin
 * @Description :BinarySearchfindPeakElement162LeetCode
 * @date 20.2.5-11:36
 * 思路：其实没有那么难，自己想复杂了。
 * 参考:在常规的二分法基础上进行修改，需要判断nums[mid],和nums[mid+1]这个条件,
 * 才能确定nums[mid]是在递增序列中还是再递减序列中。
 * 如果nums[mid]>nums[mid+1]那么nums[mid]在递减序列或者局部递减中，左侧存在
 * 一个比mid更大的值。
 * 如果nums[mid]<nums[mid+1]那么nums[mid]在递增序列或者局部递增中，右侧存在
 * 一个比mid更大的值。
 * bug1:
 * [1,2]
 * java.lang.ArrayIndexOutOfBoundsException: 2
 */
public class BinarySearchfindPeakElement162LeetCode {
    public int findPeakElement(int[] nums) {
        int start =0;
        int end = nums.length-1;
        int mid =0;
        if(start==end){
            return 0;
        }
        //解决bug1,这里为了不让mid+1越界，所以应该设置左闭右开，具体可参考：
        // https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-
        //sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
        while (start<end){
            mid = start+(end-start)/2;
            //说明mid左边的数可能更大
            if (nums[mid]>nums[mid+1]){
                end=mid;
            }else {
                start = mid+1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int [] nums={1,2,3,1};
        System.out.println(new BinarySearchfindPeakElement162LeetCode().
                findPeakElement(nums));
    }
}
