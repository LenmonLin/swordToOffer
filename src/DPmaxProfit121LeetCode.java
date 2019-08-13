/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取
 * 的最大利润。注意你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最
 * 大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 思路：用动态规划：详见https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/
 * 解释的相当到位。
 * 先考虑穷举（即画出表格，考虑所有的情况）
 * 再考虑状态转移方程
 * 最后考虑边界条件
 * 最后的最后优化成一维数组。
 * @author LemonLin
 * @Description :DPmaxProfit121LeetCode
 * @date 19.8.13-11:06
 */
public class DPmaxProfit121LeetCode {
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int [][]dp = new int[prices.length][2];
        //随着天数的增加
        for (int i=0;i<prices.length;i++){
            //考虑边界条件，
            if(i-1 ==-1){
                //第1天，没有买卖股票，没有支出。也没有收入
                dp[0][0]=0;
                //第1天，持有股票的利润，是买了股票，就相当于支出了。
                dp[0][1]=-prices[0];
                continue;
            }
            /**
             * 有两种情况需要考虑，
             * dp[i][1][0]表示第i天没有持有股票，也有两种情况：第二维表示还能允许交易一次
             *           第i-1天没有持有股票，第i天没有操作，保持原样
             *           第i-1天有股票，第i天卖了出去
             * dp[][1][1]表示持有股票
             *          第i-1天持有股票，第i天没有操作，保持原样
             *          第i-1天没有股票，第i天买了进来,d[i-1][0][1]-prices[i],而d[i-1][0][1]=0
             *          因为第二维为0，不允许交易，所以收益一定为0
             */
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
        }
        return  dp[prices.length-1][0];
    }

    public static void main(String[] args) {
        int [] prices = {7,1,5,3,6,4};
        System.out.println(new DPmaxProfit121LeetCode().maxProfit(prices));
    }
}
