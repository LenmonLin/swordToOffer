/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * @author LemonLin
 * @Description :DPnumSquares279LeetCode
 * @date 20.1.26-21:19
 * 参考：https://leetcode-cn.com/problems/perfect-squares/solution/
 * bu-zhi-shi-da-an-er-shi-dong-tai-gui-hua-lei-ti-de/
 * 思路：先思考递归，找出递归的表达式，再用记忆化搜索自顶向下，最后Dp，思考双循环。
 *  状态转移方程中：dp[i-j*j]+1加一的原因是如果做了一次i-j*j处理，说明分离了一次，
 *  也就是说组成和的完全平方数的个数要加一个。
 *  这里有两个循环，第一个循环遍历传入的n的循环，n的最少完全平方个数，是从1,2...n-1
 *  前面的最少完全平方个数得来的。
 *  第二个循环是对外层循环的某一个数，组成这个数的完全平方数的遍历，所以判断条件是
 *  i -j*j>=0。
 *  为什么状态转移方程是：Math.min(minOutput,dp[i-j*j]+1);
 *  因为可以这么想，比如对于外层循环i =13,j是从1开始遍历的。
 *  当j=1时，dp[13-1*1]=dp[12],因为1*1是用掉了组成13的一个完全平方数，所以个数加1.
 *  当j=2时，dp[13-2*2]=dp[9],同理个数要加1.
 *  ...
 *  因为dp[2]是由dp[1]推出来的，所以dp[2]包含了dp[1],同理
 *  dp[13]包含了前面所有dp[1]到dp[12]的结果得到的最小值。
 *  所以dp[13]就是最后的结果。
 */
public class DPnumSquares279LeetCode {
    public int numSquares(int n) {
        //其中每个元素 dp[i] 都存了整数i所需的最少完全平方数的个数
        //比如dp[12] = 3，表示组成整数12最少需要3个完全平方数
        int[] dp = new int[n+1];
        dp[0]=0;
        // 经过for外循环，n所包含的每个整数都取得了最少完全平方数的个数
        // dp[n]得到的也是最少个数
        for (int i=1;i<=n;i++){
            //注意这里minOutput要放在外循环的内部。
            int minOutput = Integer.MAX_VALUE;
            //经过for内循环，从组成i的完全平方数的所有可能中
            //得到最少的个数 minOutput 赋值给 dp[i]
            for (int j=1;i-j*j>=0;j++){
                minOutput=Math.min(minOutput,dp[i-j*j]+1);
            }
            dp[i]=minOutput;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new DPnumSquares279LeetCode().numSquares(13));
    }
}
