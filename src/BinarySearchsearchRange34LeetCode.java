/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在
 * 数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * @author LemonLin
 * @Description :BinarySearchsearchRange34LeetCode
 * @date 20.2.5-11:32
 * 思路：自然而然的想到了二分法。但是思路很简单，细节是魔鬼。此题关键是：
 * 寻找左侧边界、寻找右侧边界。
 * 参考大神之作：
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-
 * sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
 * bug1:
 * [1]
 * 0
 * 数组越界.
 * bug2:
 * [2,2]
 * 3
 * 数组越界
 */
public class BinarySearchsearchRange34LeetCode {
    public int[] searchRange(int[] nums, int target) {
        int [] result = {-1,-1};
        if (nums.length==0){
            return result;
        }
        int left =0;
        int right =nums.length;
        int mid=0;
        //求左边界
        while (left<right){
            mid=(left+right)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right = mid;
            }else if (nums[mid]==target){
                //设置右边界，让mid继续向左压缩
                right = mid;
            }
        }
        //解决bug1：
        if (left<nums.length&&nums[left]==target){
            result[0]=left;
        }else {
            result[0] =-1;
        }
        left=0;
        right=nums.length;
        //求右边界
        while (left<right){
            mid=(left+right)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right = mid;
            }else if (nums[mid]==target){
                //设置左边界，让mid继续向右压缩
                left = mid+1;
            }
        }
        //解决bug2：
        if (left-1>=0&&nums[left-1]==target){
            result[1]=left-1;
        }else {
            result[1] =-1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums ={2,2};
        int target = 3;

        int[] ints = new BinarySearchsearchRange34LeetCode().searchRange(nums, target);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
