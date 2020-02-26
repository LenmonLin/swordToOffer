/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一
 * 个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都
 * 是正数，第二个序列是因为它的最后一个差值为零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一
 * 些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * 示例 1:
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 * @author LemonLin
 * @Description :DPwiggleMaxLength376LeetCode
 * @date 20.2.25-23:06
 * 思路：参考：https://blog.csdn.net/tstsugeg/article/details/52025712
 * 这题算是DP中比较特殊和难的解法了。用了两个DP数组，数组之间还有关联。同时这题
 * 多多少少和LeetCode300有点关系，看不懂的话可以先看一下LeetCode300。同时为什
 * 么会有：
 *    if (nums[i-1]<nums[i]){
 *                 up[i] = down[i-1]+1;
 *                 down[i] = down[i-1];
 * 可以这么举例理解。如果nums[2]<nums[3],说明下标2到下标3是上升的。那么下标2
 * 前面如果还是上升的，就不会加了，因为题目要求的是摆动序列，那么如果下标2前面如
 * 果是下降的，这个时候再加上下标2到下标3是上升的，所以摆动序列个数要加1.其他的以
 * 此类推。
 * 关于本题说的删除不删除问题，其实就是LeetCode300中子串和子序列的区别问题。因为
 * 已经用了动态规划，所以这个问题在这里不需要特别特殊的处理。
 */
public class DPwiggleMaxLength376LeetCode {
    public int wiggleMaxLength(int[] nums) {
        if (nums==null||nums.length ==0)return 0;
        int [] up =  new int[nums.length];
        int [] down =  new int[nums.length];
        up[0]=1;
        down[0]=1;
        for(int i =1;i<nums.length;i++){
            if (nums[i-1]<nums[i]){
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            }else if (nums[i-1]>nums[i]){
                up[i] = up[i-1];
                down[i] =  up[i-1]+1;
            }else if (nums[i-1]==nums[i]){
                up[i]=up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[nums.length-1],down[nums.length-1]);
    }

    public static void main(String[] args) {
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(new DPwiggleMaxLength376LeetCode().wiggleMaxLength(nums));
    }
}
