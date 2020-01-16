/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 +
 * 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例 1:
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * 注意:
 *1、 数组非空，且长度不会超过20。
 *2、初始的数组的和不会超过1000。
 *3、 保证返回的最终结果能被32位整数存下。
 * 思路：枚举每个数面前的正负号，然后用BFS去试探是否到达了目标和。到达了就记录
 * 数量。
 * 有个问题：这样处理时间复杂度好像比较大啊，都达到了2^N
 * 答：题目对数组长度进行了限制，所以2^N还算可以接受。
 * 问题二：不知道这么处理加减的问题。
 * 答：之前认为是要把加减号用字符串处理，估计是太久没写字符串的题目了，真的被蠢哭了，
 * 直接用设置一个变量sum，然后sum+nums[i]或者sum-nums[i]即可。
 * @author LemonLin
 * @Description :DFSfindTargetSumWays494LeetCode
 * @date 20.1.15-11:43
 */
public class DFSfindTargetSumWays494LeetCode {
    int cnt =0;
    int i=-1;
    public int findTargetSumWays(int[] nums, int S) {
        int sum=0;
        dfs(nums,S,sum);
        return cnt;
    }
    public void dfs(int[] nums, int S,int sum){
        /**
         * 以下两点比较绕，暂时还没想到比较好的解决方式：先记着吧
         * 这里注意体会，不能用if(i>=nums.length)return;因为num[i]是传入的参数，同时
         * 又需要使用i++;导致i总是比num[i]快一步，所以必须用if else来减小一个i++的使用
         * 同时这里要注意体会不能用if(i==nums.length-1&&S==sum)因为可能不满足S==
         * sum的情况下也需要进入if而不是进入else再进行i++；
         */
        if (i==nums.length-1){
            if (S==sum){
                cnt++;
            }
        }else {
            i++;
            dfs(nums,S,sum+nums[i]);
            dfs(nums,S,sum-nums[i]);
            i--;
        }
    }

    public static void main(String[] args) {
        int [] nums={1, 1, 1, 1, 1};
        int S=3;
        System.out.println(new DFSfindTargetSumWays494LeetCode().
                findTargetSumWays(nums, 3));
    }
}
