/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *图片见：https://leetcode-cn.com/classic/problems/unique-paths-ii/description/
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * @author LemonLin
 * @Description :DPuniquePathsWithObstacles63LeetCode
 * @date 20.1.27-16:37
 * 思路：跟62题解题思路一样，用动态规划，然后本题可能就增加路障的判断条件。
 * 但是本题不能有LeetCode62题的先入为主的先设置1的概念，这样导致了很多弯路。
 * 是否填1，和obstacleGrid[i][j]=1有很大关系。
 * 第一步：先把边角填满：
 * 如果obstacleGrid[0][0]=0，就可以把dp[0][0]=1;return 1，结束后面的循环。
 * 如果obstacleGrid[0][0]=1，就需要把dp[0][0]=0;
 * 填写了第一个dp之后，边界如果obstacleGrid[0][j]=0，dp[0][j]=dp[0][j-1];
 * 否者dp[0][j]=0;
 * 同理：obstacleGrid[i][0]=0也一样处理。
 *  1 ,1,1,1,1,1
 *  1
 *  1
 *  1
 *  1
 *  本题不能这样边界直接填1
 *  说明到相应位置只有一种走法，比如dp[0][0]=1,说明到obstacleGrid[0][0]只有一种走法
 *  同理dp[0][1]=1,说明到obstacleGrid[0][1]只有一种走法
 *  第二步：
 *  如果obstacleGrid[i][j]=0,右下角空白处只能填的是dp[i-1][j]+dp[i][j-1]，两种方法数相加。
 *  否者obstacleGrid[i][j]==1，dp[i][j]=0;
 */
public class DPuniquePathsWithObstacles63LeetCode {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n =obstacleGrid[0].length;
        return helperUniquePaths(m,n,obstacleGrid);

    }
    public int helperUniquePaths(int m, int n,int[][] obstacleGrid) {
        //特殊情况下只有一个空格时，应该返回1，但是dp[0][0]只能存0
        /**
         * bug1：
         * 输入：
         * [[1]]
         * 输出：
         * 1
         * 预期：
         * 0
         * bug2:
         * 输入:
         * [[0]]
         * 输出
         * 0
         * 预期结果
         * 1
         */
        if (m == 1 && n == 1) {
            if (obstacleGrid[0][0] == 1) {
                return 0;
            } else {
                return 1;
            }
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //先填dp[0][0]
                if (i == 0 && j == 0) {
                    if (validOrNot(i, j, obstacleGrid)) {
                        //有一种可能
                        dp[0][0] = 1;
                    } else {
                        dp[0][0] = 0;
                    }
                    continue;
                }
                //再填dp[0][j]
                if ((i - 1 == -1 && j > 0)) {
                    if (validOrNot(i, j, obstacleGrid)) {
                        dp[i][j] = dp[0][j-1];
                    }else {
                        dp[i][j]=0;
                    }
                    continue;
                }
                //再填dp[i][0]
                if ((j - 1 == -1 && i > 0)) {
                    if (validOrNot(i, j, obstacleGrid)) {
                        dp[i][j] = dp[i-1][0];
                    }else {
                        dp[i][j]=0;
                    }
                    continue;
                }
                //最后填dp[i][j]
                if (i > 0 || j > 0) {
                    if (validOrNot(i, j, obstacleGrid)) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }else {
                        dp[i][j]=0;
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    private boolean validOrNot ( int i, int j, int[][] grid){
            //false对应1，true对应0;
            if (grid[i][j] == 1) {
                return false;
            } else {
                return true;
            }
        }
    public static void main(String[] args) {
         int[][]obstacleGrid1=
                 {{1,0}};
//       {
//             {0,0,0},
//            {0,1,0},
//            {0,0,0}
//         };
        System.out.println(new DPuniquePathsWithObstacles63LeetCode().
                uniquePathsWithObstacles(obstacleGrid1));
    }
}
