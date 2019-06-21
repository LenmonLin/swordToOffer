import java.util.ArrayList;
/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *思路:第 i阶可以由以下两种方法得到：
 * 在第 (i-1) 阶后向上爬一阶。在第 (i-2)阶后向上爬 2阶。
 * 所以到达第 ii阶的方法总数就是到第 (i-1)阶和第 (i-2)阶的方法数之和。
 * 令 dp[i]dp[i] 表示能到达第 i阶的方法总数：dp[i]=dp[i-1]+dp[i-2]
 * @author LemonLin
 * @Description :DPClimbingStairs
 * @date 19.6.21-16:47
 */
public class DPClimbingStairs {
    public int climbStairs(int n) {
        ArrayList dp = new ArrayList();
        dp.add(1);
        dp.add(2);
        if (n>=3){
            for (int i=2;i<=n-1;i++){
                dp.add(i,(int)dp.get(i-1)+(int)dp.get(i-2));
            }
        }
        return (int)dp.get(n-1);
    }

    public static void main(String[] args) {
        int n=3;
        System.out.println(new DPClimbingStairs().climbStairs(n));
    }
}
