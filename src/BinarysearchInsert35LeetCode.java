/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不
 * 存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * @author LemonLin
 * @Description :BinarysearchInsert35LeetCode
 * @date 20.3.31-23:06
 * 比较简单直白的二分查找。
 */
public class BinarysearchInsert35LeetCode {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while (left<=right){
            mid = left+(right-left)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int [] nums ={1,3,5,6};
        int target = 2;
        System.out.println(new BinarysearchInsert35LeetCode().searchInsert(nums, target));
    }
}
