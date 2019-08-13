/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖
 * 一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。随后，在第 4 天（股票价格 = 3）的时候买入，在
 * 第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 思路：可以先看121LeetCode，这里的交易次数是无限的，所以状态转移方程
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *                  = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
 *我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
 *
 * @author LemonLin
 * @Description :DPmaxProfit122LeetCode
 * @date 19.8.13-15:18
 */
public class DPmaxProfit122LeetCode {
    public int maxProfit(int[] prices) {
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

    public static void main(String[] args) {
        int[] prices={7,1,5,3,6,4};
        System.out.println(new DPmaxProfit122LeetCode().maxProfit(prices));
    }
}
