/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4)的时候卖出，
 * 这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出,
 * 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出,
 * 这笔交易所能获得利润 = 3-0 = 3 。
 * @author LemonLin
 * @Description :DPmaxProfit188LeetCode
 * @date 20.5.2-15:51
 * 思路：
 * 出现了一个超内存的错误，原来是传入的 k 值会非常大，dp 数组太大了。现在想想，交
 * 易次数 k 最多有多大呢？
 * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过
 * n/2，如果超过，就没有约束作用了，相当于 k = +infinity。这种情况是之前解决过的。
 */
public class DPmaxProfit188LeetCode {
    public int maxProfit(int k, int[] prices) {
        if(k>prices.length/2){
            return maxProfitleetcode122(prices);
        }
        if (prices.length==0)return 0;
        int dp[][][] = new int[prices.length][k+1][2];
        for(int i=0;i<prices.length;i++){
            // base case
            dp[i][0][0] = 0;//至今为止没有交易，收益为0
            dp[i][0][1] = Integer.MIN_VALUE;//交易了0次，但持有股票，不符合规则
            for (int j=1;j<=k;j++){
                if (i-1==-1){
                    dp[i][j][0] = 0;//第一天买入t次，当天卖出t次,收入为0
                    dp[i][j][1] = -prices[i];//甭管第一天买多少次，反正最后少卖一次，持有了股票
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i]);
            }
        }
        return dp[prices.length-1][k][0];
    }
    public int maxProfitleetcode122(int[] prices) {
        if (prices.length==0)return 0;
        int dp[][] = new int[prices.length][2];
        for(int i=0;i<prices.length;i++){
            if (i-1==-1){
                dp[0][0]=0;
                dp[0][1]=-prices[i];
                continue;
            }
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //注意这里跟LeetCode121对比，多了dp[i-1][0],因为多了次数不限制。
            //如果次数为1的话，就是dp[i-1][0][0]的时候不能交易，所以之前的这个数为0；
            //就可以省略
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
