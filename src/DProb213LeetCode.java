import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有
 * 的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
 * 装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，
 * 能够偷窃到的最高金额。
 * 示例 1:
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为
 * 他们是相邻的。
 * 示例 2:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * @author LemonLin
 * @Description :DProb213LeetCode
 * @date 20.1.28-10:55
 * 思路：和LeetCode198对比，输入变成的循环数组，所以数组最后一个和第一个不能同时
 * 偷，考虑将循环数组拆分成两个数组，第一个数组为除了最后一个元素的数组num1[0,
 * nums.length()-2],第二个数组为除了第一个数组元素的数组num2[1,nums.length()-1];
 * 最后的结果是去这两个数组的可偷的最大值动态规划值。即状态方程为
 * dp1[i]=max(dp[i-2],dp[i-1]+nums[i]);(0<=i<=nums.length()-2)
 * dp2[i]=max(dp[i-2],dp[i-1]+nums[i]);(1<=i<=nums.length()-1)
 * dp = max(dp1,dp2)
 */
public class DProb213LeetCode {
    public int rob(int[] nums) {

        //实际做法中不能申请两个dp[],会出很大的bug,直接用(Arrays.copyOfRange)解决问题。
/*        int []dp1 =new int[nums.length];
        int []dp2 =new int[nums.length];
        dp1[0]=nums[0];
        dp1[1]=nums[1];
        dp2[0]=nums[1];
        dp2[1]=nums[2];
        dp1[2]= Math.max(nums[0],nums[1]);
        dp2[2]= Math.max(nums[1],nums[2]);
        if (nums.length==0)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        if (nums.length==3){
            return Math.max(dp1[2],dp2[2]);
        }

        for (int i=3;i<=nums.length-2+3;i++){
            dp1[i]=Math.max(dp1[i-2],dp1[i-1]+nums[i]);
        }
        for (int i=3;i<=nums.length-1+3;i++){
            dp2[i]=Math.max(dp2[i-2],dp2[i-1]+nums[i]);
        }
        return Math.max(dp1[nums.length-2],dp2[nums.length-1]);*/
        if (nums.length==0)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        //Arrays.copyOfRange(nums,a,b)范围不包括b，即为nums[a]--nums[b-1]
        return Math.max(helperRob(Arrays.copyOfRange(nums,0,
                nums.length-1)) ,helperRob( Arrays.copyOfRange(nums,1,
                nums.length)));
    }

    private int helperRob(int [] nums){

        int [] dp =new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }
    public static void main(String[] args) {
        int[] nums={2,3,2};
        System.out.println(new DProb213LeetCode().rob(nums));
    }
}
