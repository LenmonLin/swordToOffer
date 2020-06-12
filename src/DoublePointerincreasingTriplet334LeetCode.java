/**
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * 数学表达式如下:
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: true
 * 示例 2:
 * 输入: [5,4,3,2,1]
 * 输出: false
 * @author LemonLin
 * @Description :DoublePointerincreasingTriplet334LeetCode
 * @date 20.6.12-10:35
 * 思路：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/c-xian-xing-shi-jian-fu-za-du-xiang-xi-jie-xi-da-b/
 * 很巧妙，利用双指针
 */
public class DoublePointerincreasingTriplet334LeetCode {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i=0;i<nums.length;i++){
            if (small>nums[i]){
                small = nums[i];
                continue;
            }
            /**
             * 加&&small<nums[i]是为了处理以下bug:
             * 输入:
             * [1,1,-2,6]
             * 输出
             * true
             * 预期结果
             * false
             */
            if (mid>nums[i]&&small<nums[i]){
                mid = nums[i];
                continue;
            }
            if (mid<nums[i]){
                return true;
            }
        }
        return false;
    }
}
