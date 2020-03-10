/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度
 * 最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法
 * @author LemonLin
 * @Description :SlidingwindowminSubArrayLen209LeetCode
 * @date 19.6.23-17:34
 * 思路：滑动窗口法
 * 比较简单，利用滑动窗口双指针，先拓展右窗口，当数组中的和>=s,再缩小左窗口，如此
 * 反复，期间更新最小的长度
 * 代码模板LeetCode76
 */
public class SlidingwindowminSubArrayLen209LeetCode {
    public int minSubArrayLen(int s, int[] nums) {
        //flag的存在是为了防止，如果不存在这样的子数组，也就是没有进入满足条件的循环，
        // 就返回0
        boolean flag = true;
        int minOfLength=s;
        int sum=0;
        int left=0;
        int right =0;
        while (right<nums.length){
            sum=sum+nums[right];
            right++;
            while (sum>=s){
                flag = false;
                if (right-left<minOfLength){
                    minOfLength=right-left;
                }
                sum-=nums[left];
                left++;
            }
        }
        if (flag)return 0;
        return minOfLength;
    }

    public static void main(String[] args) {
        int s=7;
        int[] nums={2,3,1,2,4,3};
        System.out.println(new SlidingwindowminSubArrayLen209LeetCode().minSubArrayLen(s, nums));
    }
}
