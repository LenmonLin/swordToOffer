/**
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 * @author LemonLin
 * @Description :DPnthUglyNumber264LeetCode
 * @date 20.2.1-11:02
 * 思路：首先丑数的求解规则是，
 *      先把一个数不断的除以2，直到不能除了，是否等于1，等于1，即为丑数。
 *          否则继续除以3，直到不能除了，是否等于1，等于1，即为丑数。
 *                 否则继续除以5，直到不能除了，是否等于1，等于1，即为丑数。
 *                        否则不是丑数。
 * 这题利用动态规划思路：dp[i]存储第i+1个丑数，比如dp[0]=1是第0+1个丑数。
 * 因为要按顺序存储，小的丑数乘以2，或者3，或者5得到的数都是丑数。所以按顺序
 * 把小的丑数乘以2，或者3，或者5，取这三个中最小的按顺序存放。所以推出：
 * dp[i]=min{dp[i-1]*2,dp[i-1]*3,dp[i-1]*5};
 *但是这个有个问题，可能dp[i-2]*3<dp[i-1]*2。按理说dp[i-2]*3应该放在i，比如
 * dp[2]=3,不是等于min{dp[1]*2=4,dp[1]*3=6,dp[1]*5=10}，所以上面的状态转移
 * 方程有问题，没有穷举，漏掉了什么。注意到dp[2]应该等于dp[i-2]*3,所以是下标出现了
 * 问题。这里要用另外的三个独立变量记录min{}中的下标。不能用i。仔细体会一下。
 */
public class DPnthUglyNumber264LeetCode {
    public int nthUglyNumber(int n) {
        int[] dp=new int[n];
        dp[0]=1;
        int i2=0;
        int i3=0;
        int i5=0;
        for (int i=1;i<n;i++){
            dp[i]=Math.min(dp[i2]*2,Math.min(dp[i3]*3,dp[i5]*5));
            if (dp[i]==dp[i2]*2)i2++;
            if (dp[i]==dp[i3]*3)i3++;
            if (dp[i]==dp[i5]*5)i5++;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int n=10;
        System.out.println(new DPnthUglyNumber264LeetCode().nthUglyNumber(n));
    }
}
