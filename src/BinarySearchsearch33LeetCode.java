/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * @author LemonLin
 * @Description :BinarySearchsearch33LeetCode
 * @date 20.2.5-11:33
 * 思路：自然而然联想到用二分法：怎么用？
 * 1 2 3 4 5 6 7 可以大致分为两类，
 * 第一类 2 3 4 5 6 7 1 这种，也就是 nums[left] <= nums[mid]。此例子中就
 * 是 2 <= 5。说明左半部分是递增的。这种情况下，前半部分有序。
 *      因此如果nums[left]<target&&target<nums[mid]，则在左半部分找，
 *      否则去右半部分找。
 * 第二类 6 7 1 2 3 4 5 这种，也就是 nums[right] > nums[mid]。此例子中就
 * 是 6 > 2。这种情况下，右半部分有序。
 *      因此如果 nums[mid]<target&&target<nums[right]就在右半部分寻找，
 *      否则去左半部分找。
 */
public class BinarySearchsearch33LeetCode {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left =0;
        int right = nums.length-1;
        int mid=0;
        //注意这里是等号
        while (left<=right){
            mid = left+(right-left)/2;
            if (target == nums[mid]){
                return mid;
            }
            //左半部分递增
            if (nums[left]<=nums[mid]){
                //这里要用等号
                if(nums[left]<=target&&target<=nums[mid]) {
                    right = mid - 1;
                }else {
                    left = mid+1;
                }
            }else {
                if (nums[mid]<=target&&target<=nums[right]){
                    left =mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int [] nums={4,5,6,7,0,1,2};
        int target =0;
        System.out.println(new BinarySearchsearch33LeetCode().search(nums, target));
    }
}
