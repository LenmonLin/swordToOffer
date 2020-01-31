/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子
 * 集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 * @author LemonLin
 * @Description :DPcanPartition416LeetCode
 * @date 20.1.31-11:45
 * 思路：动态规划，01背包问题（变形），看了题解才会，尴尬。
 * 参考：https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
 */
public class DPcanPartition416LeetCode {
    public boolean canPartition(int[] nums) {
        int sum =0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if (sum%2!=0)return false;
        int target = sum/2;
        boolean [][] dp = new boolean[nums.length][target+1];

        //先把能填的边界填了，然后状态转移方程中后面的dp值,才能根据前面的dp值生成
        //填写第0行
        if (nums[0]<target){
            dp[0][nums[0]]=true;
        }
        for (int i=1;i<nums.length;i++){
            for (int j=0;j<=target;j++){
                if (j==nums[i]){
                    dp[i][nums[i]]=true;
                    continue;
                }
                if (j-nums[i]>=0){
                    dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][target];
    }
    public static void main(String[] args) {
        int [] nums={1, 2, 3, 5};
        System.out.println(new DPcanPartition416LeetCode().canPartition(nums));
    }
}
