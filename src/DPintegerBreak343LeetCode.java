/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * @author LemonLin
 * @Description :DPintegerBreak343LeetCode
 * @date 20.1.26-16:44
 * 思路：
 * https://www.liwei.party/2018/09/17/leetcode-solution/dynamic-programming-3/
 * 为什么这个动态规划，用的是双循环？首先根据上述链接，本题可以画出递归树。
 * A、动态规划基本上由递归，到记忆化搜索，最后再到动态规划这种演变过程。
 * 这里的递归和记忆化搜索基本都要用到递归，所以很类似回溯专题中的有两个循环，一
 * 种隐藏的外循环，一种明着写的内循环，本题也是，本题的外循环和内循环的连接点是，
 * 内循环的边界。
 *   private int breakInteger(int num) {
 *         for (int i = 1; i < num; i++) {
 * 也就是num部分，因为分隔整数，比如10，分割了一个3之后，剩下的是7，还是要从1
 * 到7中间分割一个，所以循环是这样写for (int i = 1; i < num; i++) {。
 * B、动态规划和递归和记忆化在代码上最大的不同是没有递归函数的存在，所以对于这种
 * 类似回溯的树，必须把外内循环两个循环显式的写出来，不能通过递归隐藏外循环了。动
 * 态规划的二重循环的关系是这样的：
 * for (int i=2;i<=n;i++){
 *          for (int j=1;j<i;j++){
 * 外循环管理树高（最少两层，因为题目要求至少两个数，最多是n层，每一层都分配，最
 * 后结果就是从根节点到叶子节点这一路径的所有节点的数的乘积最大），内循环管理每个
 * 节点的分支。
 *
 * 至于状态转移方程，为什么要比较三个数，
 * 1、dp[i]跟自己比较，也就比较，比如n=10可以拆分3,3,4,其中的3还可以在拆分但是都比原来
 * 的3小，所以选取不拆分，这就是和自己比较；
 * 2、n*(n-i)  因为题目要求integerBreak是将一个正整数拆分为至少两个正整数的和，
 * 也就是说i*dp[n-i]至少是三个整数的积(i一个数，dp[n-1]至少两个数)，所以我们缺少了一种
 * 只有两个数不拆分的情况，也就是n*(n-i)
 * 3、i*dp[n-i] 这个比较好理解，这里要注意的是dp[n-i] 是至少两个数以上
 *
 * 这里用数学归纳法很难想到状态转移方程，如果用递归思路可以想到dp[i] =max(i*dp[n-i])
 * 但是这个不是全部，注意题目至少两个数，所以dp[i]表示了至少两个数的乘积，所以for
 * 的外层循环必须从2开始，不能为1，如果为1，不能满足至少两个数的要求。
 * 本题真正的难点是另外两个n*(n-i)与dp[i]的参数，这个在上面已经说了，就不赘述了。
 * 总结一下，这题真的很难想。
 */
public class DPintegerBreak343LeetCode {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0] =0;
        dp[1]=1;
        for (int i=2;i<=n;i++){
            for (int j=1;j<i;j++){
                dp[i] = max3(dp[i],j*(i-j),j*dp[i-j]);
            }
        }
        return dp[n];
    }
    private int max3(int a,int b,int c){
        int tempMax = Math.max(a,b);
        return Math.max(tempMax,c);
    }

    public static void main(String[] args) {
        System.out.println(new DPintegerBreak343LeetCode().integerBreak(10));
    }
}
