import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * 思路：动态规划，这题的状态转移方程比较难写。
 * 参考：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
 * @author LemonLin
 * @Description :DPlengthOfLIS300LeetCode
 * @date 20.1.30-20:24
 */
public class DPlengthOfLIS300LeetCode {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        //dp数组要初始化为1，因为之后要用到默认的dp[i]进行比较
        Arrays.fill(dp,1);
        for (int i=0;i<nums.length;i++){
            for (int j=0;j<i;j++){
                if (nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        //遍历寻找出最大的dp值即可。
        int result =0;
        for (int k=0;k<dp.length;k++){
            if (dp[k]>result){
                result = dp[k];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums = {};
        System.out.println(new DPlengthOfLIS300LeetCode().lengthOfLIS(nums));
    }
}
