/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃
 * 的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一
 * 晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金
 * 额 = 1 + 3 = 4 。
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 思路：
 * 可以看出，根据输入示例1自然而然的看输入数据的时候从后往前联想选max(四个数)=
 * max(三个数)+第四个数，但是这样会有问题，就是以为有相邻两个数不能都取的限制。
 * 可以考虑从前往后开始算规则。这里的思路是
 * 难点一：列举三个例子，1，max(1)=1；
 *                      1,2 max(1,2) =2;
 *                      1,2,3max(1,2,3)=max(1+3,2)=4;
 *                      1,2,3,1max(1,2,3,1) = max(max(1,2)+1,max(1+3,2))=4
 *                      所以推算出max(input(4个数)) = max(input(4个数减2)+input[第四],
 *                      max(4-1个数)))
 * 难点二：         选择 f(–1) = f(0) = 0 为初始情况
 * 所以最后退出状态转移方程：dp[i]=max(dp[i-1],dp[i-2]+nums[i])
 * @author LemonLin
 * @Description :DProb198LeetCode
 * @date 19.11.19-11:07
 */
public class DProb198LeetCode {
    public int rob(int[] nums) {
        //前几个特殊情况特殊处理。
        if (nums.length==0)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        int [] dp =new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,1};
        System.out.println(new DProb198LeetCode().rob(nums));
    }
}
