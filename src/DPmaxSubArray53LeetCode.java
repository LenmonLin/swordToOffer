/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * @author LemonLin
 * @Description :DPmaxSubArray53LeetCode
 * @date 19.8.13-19:51
 * 参考：https://leetcode-cn.com/problems/maximum-subarray/solution/
 * dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/#comment
 *  思路：本题难点是怎么实现O(n)的解法，如果不考虑这个O(n),可以用两个for循环的
 *  dp[][]来解决。但是题目限定了O(n)。自己想的无法在O(n)解决的主要原因是，即使知道
 *  设置dp[i]表示0~i的最大和的连续子数组，但是可能存在(3,i)>(0,i)因为可能(0,2)都是
 *  负数，这种情况怎么处理，这里就是本题的重难点，看状态转移方程很巧妙的处理了这个
 *  问题：dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);这里发现如果前面的dp[i-1]小于0，
 *  此时突然来了一个nums[i]大于0，那么应该把dp[i]赋值为nums[i],这里细品一下，这样
 *  就解决了之前可能(3,i)>(0,i)的情况，即使(3,i)>(0,i)，dp[i]也是存的最大的值，即(3,i)。
 *这样处理的话，dp[i]不一定比dp[i-1]大，需要完全遍历一边才可以。这也是本题的难点。
 * 本题有点像LeetCode300，无法用数学归纳法解决。有点特殊。
 */
public class DPmaxSubArray53LeetCode {
    public int maxSubArray(int[] nums) {
        if (nums.length==1)return nums[0];
        int []dp = new int[nums.length];
        int sumMax =0;
        for (int i=0;i<nums.length;i++){
            //设定边界
            if (i-1==-1){
                dp[i]=nums[0];
                sumMax = dp[i];
                continue;
            }
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            sumMax = Math.max(sumMax,dp[i]);
        }

        return sumMax;
    }

    public static void main(String[] args) {
        int [] nums ={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new DPmaxSubArray53LeetCode().maxSubArray(nums));
    }
}
