/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * @author LemonLin
 * @Description :BinarySearchsearch81LeetCode
 * @date 20.2.5-11:36
 * 思路：参考LeetCode33的解题思路。这里多了一种情况，因为数可能重复。所以可能有
 * 如下的情况：
 * 10111 和 1110111101 这种。此种情况下 nums[left] == nums[mid]，分不清
 * 到底是前面有序还是后面有序，此时 left++ 即可。相当于去掉一个重复的干扰项。
 */
public class BinarySearchsearch81LeetCode {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return false;
        }
        int left =0;
        int right = nums.length-1;
        int mid=0;
        //注意这里是等号
        while (left<=right){
            mid = left+(right-left)/2;
            if (target == nums[mid]){
                return true;
            }
            if (nums[left]==nums[mid]){
                left++;
                continue;
            }
            //左半部分递增
            if (nums[left]<nums[mid]){
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
        return false;
    }

    public static void main(String[] args) {
        int [] nums={2,5,6,0,0,1,2};
        int target =3;
        System.out.println(new BinarySearchsearch81LeetCode().search(nums, target));
    }
}
