/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路
 * 径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * @author LemonLin
 * @Description :DPminPathSum64LeetCode
 * @date 20.1.26-15:30
 * 思路：典型的动态规划，先画图，推算出状态转移方程，本题一直没想明白重叠子问题出现
 * 在什么地方，是个没想明白的bug?
 * 本题也可以不申请dp数组直接在gird数组上修改。更省空间
 */
public class DPminPathSum64LeetCode {
    public int minPathSum(int[][] grid) {
        int[][]dp = new int [grid.length][grid[0].length];
        return helperMinPathSum(grid,dp);
    }
    private  int helperMinPathSum(int[][] grid,int[][] dp){
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (i==0&&j==0){
                    dp[i][j]=grid[i][j];
                    //下边界
                }else if (i-1<0){
                    dp[i][j]=dp[i][j-1]+grid[i][j];
                    //左边界
                }else if (j-1<0){
                    dp[i][j]=dp[i-1][j]+grid[i][j];
                }else {
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        int [][]grid ={
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(new DPminPathSum64LeetCode().minPathSum(grid));
    }
}
