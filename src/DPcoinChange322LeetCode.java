/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金
 * 额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * @author LemonLin
 * @Description :DPcoinChange322LeetCode
 * @date 20.1.31-10:52
 * 思路：动态规划，01背包问题，(跟最初的01背包有点出路，这题是一维数组，01背包是
 * 二维数组，但是思想很相通)看了题解才会的，尴尬。
 * 参考：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=
 * 2247484731&idx=1&sn=f1db6dee2c8e70c42240aead9fd224e6&chksm
 * =9bd7fb33aca07225bee0b23a911c30295e0b90f393af75eca377caa4598ffb20
 * 3549e1768336&mpshare=1&scene=23&srcid=0131KNcTcNgSsaId3LNYbgG
 * j&sharer_sharetime=1580441992819&sharer_shareid=8684daea4af3a776
 * 035606db025c3099#rd
 *
 */
public class DPcoinChange322LeetCode {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i=1;i<=amount;i++){
            //注意这里是设置成amoun+1,不是i+1
            /**
             * dp数组初始化为amount + 1呢，因为凑成amount金额的硬币数最多只可能
             * 等于amount（全用 1 元面值的硬币），所以初始化为amount + 1就相当于
             * 初始化为正无穷，便于后续取最小值。
             */
            dp[i]=amount+1;
        }
        for (int i=1;i<=amount;i++){
            for (int j=0;j<coins.length;j++){
                if (i-coins[j]>=0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //这里是通过初始化为amount+1返回-1，而不是dp[amount]=-1,这样做简化了
        //一些思考。很巧妙。细品。
        if (dp[amount]==amount+1){
            return -1;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins ={2};
        int amout =3;
        System.out.println(new DPcoinChange322LeetCode().coinChange(coins, amout));
    }
}
