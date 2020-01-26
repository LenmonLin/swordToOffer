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
 * bug,还是没想明白双循环，至于状态转移方程，为什么要比较三个数，
 * 1、dp[i]跟自己比较，也就比较，比如n=10可以拆分3,3,4,其中的3还可以在拆分但是都比原来
 * 的3小，所以选取不拆分，这就是和自己比较；
 * 2、n*(n-i)  因为题目要求integerBreak是将一个正整数拆分为至少两个正整数的和，
 * 也就是说i*dp[n-i]至少是三个整数的积(i一个数，dp[n-1]至少两个数)，所以我们缺少了一种
 * 只有两个数不拆分的情况，也就是n*(n-i)
 * 3、i*dp[n-i] 这个比较好理解，这里要注意的是dp[n-i] 是至少两个数以上
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
