/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为
 * “Finish”）。
 * 问总共有多少条不同的路径？
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * 思路：动态规划：先把边角填满，
 * 1 ,1,1,1,1,1
 * 1
 * 1
 * 1
 * 1
 * 右下角空白处只能填的是dp[i-1][j]+dp[i][j-1]，两种方法数相加。
 * 以上是第一种方法
 * @author LemonLin
 * @Description :DPuniquePaths62LeetCode
 * @date 19.8.14-16:16
 * 思路二：
 * 1、先想递归，从递归角度来想，举例m=3,n=2;递归函数helper(3,2);
 * 第一步是假设处理(0,0)->(0,1)+helper(3,1);表示(3,1)没有处理。
 * 第二步假设(0,1)->(1,1)+helper(2,1),表示(2,1)没有处理。
 * 以此类推，最后处理了可能是(2,2)->(3,2)或者是(3,1)->(3,2)
 * 2、以上是递归的思路，是从上往下的，而DP的解决是从下往上的。所以一般先填dp[3][2]
 * 再填dp[2][2]或者dp[3][1]等等，以此类推。那么状态转移方程为什么是：
 * dp[i][j]=dp[i+1][j]+dp[i][j+1];
 * 因为dp[i][j]设置为i,j到达m,n的路径数。那么因为[i][j]的下一步只有两种情况：分别
 * 是往[i+1][j]走或者往[i][j+1]走，如果假设[i+1][j]到m,n的路径数为a,[i][j+1]到m,n
 * 的路径数为b,那么可以推测出[i][j]到m,n的路径数为a+b。所以得出状态转移方程如上。
 * 3、状态的初始情况，也就是从最后一行边界或者最后一列边界到达目的地m，n都只有一种
 * 方式，所以边界都只能填1。
 * 其实这样想想，如果反向想明白了，用正向解法也可以。
 */
public class DPuniquePaths62LeetCode {
    //自底向上的动态规划。
    public int uniquePaths2(int m, int n) {
        int [][]dp = new int[m][n];
        for (int i =m-1;i>=0;i--){
            for (int j=n-1;j>=0;j--){
                //边界填写1
                if ((i==m-1&&j>=0)||(j==n-1&&i>=0)){
                    dp[i][j]=1;
                    continue;
                }
                dp[i][j]=dp[i+1][j]+dp[i][j+1];
            }
        }
        return dp[0][0];
    }
    public int uniquePaths(int m, int n) {
        int [][]dp = new int[m][n];
        for (int i =0;i<m;i++){
            for (int j=0;j<n;j++){
                //边界填写1
                if ((i-1==-1&&j>=0)||(j-1==-1&&i>=0)){
                    dp[i][j]=1;
                    continue;
                }
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new DPuniquePaths62LeetCode().uniquePaths2(3, 2));
    }
}
