/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在
 * 该位置可以跳跃的最大长度。判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以
 * 你永远不可能到达最后一个位置。
 * @author LemonLin
 * @Description :ArraycanJump55LeetCode
 * @date 20.2.1-19:20
 * 思路：
 * 参考：https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/
 *1、如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为起跳点。
 *2、可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
 *3、 如果可以一直跳到最后，就成功了。
 * bug1：
 * 输入:
 * [0]
 * 输出
 * false
 * 预期结果
 * true
 * bug2:
 * 输入:
 * [2,0,0]
 * 输出
 * false
 * 预期结果
 * true
 * bug3:
 * 输入:
 * [4,0,4,2,2,0,1,3,3,0,3]
 * 输出
 * false
 * 预期结果
 * true
 * 执行时间较长：有待优化
 * 执行用时 :
 * 634 ms, 在所有 Java 提交中击败了8.43%的用户
 * 内存消耗 :
 * 41.4 MB, 在所有 Java 提交中击败了14.64%的用户
 */
public class ArraycanJump55LeetCode {
    public boolean canJump(int[] nums) {
        //bug1修正
        if (nums.length==1)return true;
        int tempMax=0;
        for (int i=0;i<=tempMax;i++){
            for (int j=i;j<=i+nums[i];j++){
                //bug3：这里修正需要把tempMax加进去比较。
                tempMax=Math.max(Math.max(i+nums[i],j+nums[j]),tempMax);
                //bug2修正nums.length需要减一.
                if (tempMax>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums={3,2,1,0,4};
        System.out.println(new ArraycanJump55LeetCode().canJump(nums));
    }
}
