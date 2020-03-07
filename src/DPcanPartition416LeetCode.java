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
 *参考一难度太大，放弃。很难理解。
 * 参考2 ：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/
 * dang-zuo-bei-bao-wen-ti-shi-yong-dpjie-jue-by-user/
 *解法1不容易理解，直接放弃。留作纪念
 * 这题很难，首先把问题需要转化为寻找目标值/2的数，如果给定的数组中目标值/2的数能够
 * 满足要求，那么剩下的数一定也等于另外一半。而且本题求得不是最值，求得是Boolean
 * 问题。不好搞。这里考虑不设置DP数组为Boolean型。考虑直接套用01背包的思路：
 * 这里把原问题的价值变量去掉，用重量代替状态转移中的价值。
 * dp[i][j]表示背包容量为j,用了编号到(i)的物品,背包能装的最大重量是多少。
 * 这里把target看着背包的容量。输入数组表示每个物品的重量。
 * 把经典01背包问题中的总价值用总重量替代。
 * 然后这里判断dp[i][j]如果等于target，那么就是说明，刚好可以分割。返回true
 * 照着经典01背包问题的模板可以写成解法2。
 * 解法3，只是把判断条件前移，直接到填写DP table过程中去判断是否有等于target。
 * 这里的dp[i][j]设置为在长度为(0,i)的长度范围内，是否有存在的和等于j;
 * bug1:
 * 输入:
 * [3,3,3,4,5]
 * 输出
 * false
 * 预期结果
 * true
 */
public class DPcanPartition416LeetCode {
    //21ms
    public boolean canPartition3(int[] nums) {
        int sum =0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if (sum%2!=0)return false;
        int target = sum/2;
        int [][] dp = new int[nums.length][target+1];
        //先把能填的边界填了，然后状态转移方程中后面的dp值,才能根据前面的dp值生成
        //填写第0行
        for (int j=1;j<=target;j++){
            if (nums[0]>target){
                dp[0][j] = 0;
            }else {
                dp[0][j]=nums[0];
            }
            if (dp[0][j]==target){
                return true;
            }
        }
        for (int i=1;i<nums.length;i++){
            for (int j=0;j<=target;j++){
                if (j<nums[i]){
                    dp[i][j]=dp[i-1][j];
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-nums[i]]+nums[i]);
                }
                if (dp[i][j]==target){
                    return true;
                }
            }
        }
        return false;
    }
    //70ms
    public boolean canPartition2(int[] nums) {
        int sum =0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if (sum%2!=0)return false;
        int target = sum/2;
        int [][] dp = new int[nums.length][target+1];
        //先把能填的边界填了，然后状态转移方程中后面的dp值,才能根据前面的dp值生成
        //填写第0行
      for (int j=1;j<=target;j++){
          if (nums[0]>target){
              dp[0][j] = 0;
          }else {
              dp[0][j]=nums[0];
          }
      }
    for (int i=1;i<nums.length;i++){
        for (int j=0;j<=target;j++){
            if (j<nums[i]){
                dp[i][j]=dp[i-1][j];
            }else {
                //注意这里是+num[i]和经典的01背包问题不一样，需要注意
                dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-nums[i]]+nums[i]);
            }
        }
    }
        for (int i=0;i<nums.length;i++){
            for (int j =0;j<=target;j++){
                if (dp[i][j]==target){
                    return true;
                }
            }
        }
        return false;
    }
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
        int [] nums=
                {1, 2, 3, 5};
//        {1, 5, 11, 5};
//        {3,3,3,4,5};
        System.out.println(new DPcanPartition416LeetCode().canPartition2(nums));
    }
}
