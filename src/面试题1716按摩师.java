/**
* 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预
 * 约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩
 * 师找到最优的预约集合（总预约时间最长），返回总的分钟数。
* 注意：本题相对原题稍作改动
* 示例 1：
* 输入： [1,2,3,1]
* 输出： 4
* 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
* 示例 2：
* 输入： [2,7,9,3,1]
* 输出： 12
* 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
* 示例 3：
* 输入： [2,1,4,5,3,1,1,3]
* 输出： 12
* 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 * @author LemonLin
 * @Description :面试题1716按摩师
 * @date 20.3.24-20:29
 * 思路，和打家劫舍LeetCode198一模一样
 */
public class 面试题1716按摩师 {
    public int massage(int[] nums) {
        if (nums.length==0)return 0;
        if (nums.length==1)return nums[0];
        int [] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int [] nums ={2,7,9,3,1};
        System.out.println(new 面试题1716按摩师().massage(nums));
    }
}
