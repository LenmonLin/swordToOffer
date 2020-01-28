/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的
 * 交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * @author LemonLin
 * @Description :DPmaxProfit309LeetCode
 * @date 20.1.28-19:26
 * 思路：用动态规划：详见https://leetcode-cn.com/problems/
 * best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/
 */
public class DPmaxProfit309LeetCode {
    public int maxProfit(int[] prices) {
        //股票交易的天数
        int days=prices.length;
        //股票持有和不持有的状态。0表示没有持有，1表示持有。
        if (days<2)return 0;
        int isHave =2;
        //表示最大的收益
        int[][]dp = new int[days][isHave];

        for (int i=0;i<days;i++){
            if (i-2==-2){
                /**
                *参考：dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                 *          当i=0,dp[0][0]=Math.max(dp[-1][0],dp[-1][1]+prices[0])
                 *   其中，dp[-1][0]=0,-1天表示还没开始，所以应该为0
                 *   dp[-1][1]+prices[0]=不存在，因为dp[-1][1]还没开始不可能持有股票
                 *    所以dp[0][0]=Math.max(0,不存在)=0;
                 *            dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
                 *      dp[0][1]=Math.max(dp[-1][1],dp[-2][0]-prices[0])
                 *      同理可得dp[-1][1]=不存在，
                 *      d[0][1]=-prices[0]
                 */
                dp[i][0]=0;
                dp[i][1]=-prices[i];
                continue;
            }
            if (i-2==-1){
                /**
                 *参考：dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                 *                  当i=1,dp[1][0]=Math.max(dp[0][0],dp[0][1]+prices[0])
                 *        dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
                 *              dp[1][1]=Math.max(dp[0][1],dp[-1][0]-prices[1])
                 *              可得dp[-1][0]+prices[0]=不存在，因为dp[-1][1]还没开始不可能持有股票
                 */
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[0][1],-prices[i]);
                continue;
            }
            /**
             *状况一：今天我没有持有股票，有两种可能：
             * 要么是我昨天就没有持有，然后今天选择 休息不操作，所以我今天还是没有持有；
             * 要么是我昨天持有股票，但是今天我 卖了 了，所以我今天没有持有股票了。
             *状况二：今天我持有着股票，有两种可能：
             * 要么我昨天就持有着股票，然后今天选择 休息不操作，所以我今天还持有着股票；
             * 要么我昨天本没有持有，但今天我选择 买入，所以今天我就持有股票了。
             */
            //不持有的最大收益，昨天有股票，卖了就算收益，所以要加上price[i]
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //持有股票的最大收益,买入股票，钱付出了，所以要减price[i]
            dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
        }
        return dp[days-1][0];
    }

    public static void main(String[] args) {
        int[] prices={1,2};
        System.out.println(new DPmaxProfit309LeetCode().maxProfit(prices));
    }
}
