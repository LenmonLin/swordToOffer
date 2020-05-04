/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 * @author LemonLin
 * @Description :Arraysjump45LeetCode
 * @date 20.5.4-11:24
 * 参考：https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/
 * 贪心算法；每次都选最远的，选最远之后，跳跃次数就要加1，这题的难点是需要设置一个
 * 暂时最远的界限，到了这个界限之后才能更新跳跃次数和新的最远界限
 */
public class Arraysjump45LeetCode {
    public int jump(int[] nums) {
        int tempMax = 0;
        int tempEnd =0;
        int result =0;
        /**
         * 这里要注意一个细节，就是 for 循环中，i < nums.length - 1，少了末尾。因为开
         * 始的时候边界是第 0 个位置，steps 已经加 11 了。如下图，如果最后一步刚好跳
         * 到了末尾，此时 steps 其实不用加 11 了。如果是 i < nums.length，i
         * 遍历到最后的时候，会进入 if 语句中，steps 会多加 1。
         */
        for (int i =0;i<nums.length-1;i++){
            tempMax = Math.max(tempMax,i+nums[i]);
            if (tempEnd == i){
                tempEnd = tempMax;
                result++;
            }
        }
        return result;
    }
}
