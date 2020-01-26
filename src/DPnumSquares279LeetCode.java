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
 * 思路：先思考递归，找出递归的表达式，再用记忆化搜索自顶向下，最后Dp，思考双循环。
 *  状态转移方程中：dp[i-j*j]+1加一的原因是如果做了一次i-j*j处理，说明分离了一次，
 *  也就是说组成和的完全平方数的个数要加一个。
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
