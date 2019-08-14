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
 * 0 ,1,1,1,1,1
 * 1
 * 1
 * 1
 * 1
 * 右下角空白处只能填的是dp[i-1][j]+dp[i][j-1]，两种方法数相加。
 * @author LemonLin
 * @Description :DPuniquePaths62LeetCode
 * @date 19.8.14-16:16
 */
public class DPuniquePaths62LeetCode {
    public int uniquePaths(int m, int n) {
        //特殊情况下只有一个空格时，应该返回1，但是dp[0][0]只能存0
        if (m==1&&n==1){
            return 1;
        }
        int [][]dp = new int[m][n];
        for (int i =0;i<m;i++){
            for (int j=0;j<n;j++){
                if ((i-1==-1&&j==0)||(i==0&&j-1==-1)){
                    dp[i][j]=0;
                    continue;
                }
                if ((i-1==-1&&j>0)||(j-1==-1&&i>0)){
                    dp[i][j]=1;
                    continue;
                }
                if (i>=0||j>=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                    continue;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new DPuniquePaths62LeetCode().uniquePaths(7, 3));
    }
}
