/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 输出: 4
 * @author LemonLin
 * @Description :DPmaximalSquare221LeetCode
 * @date 20.1.31-22:33
 * 思路：题目看起来阅读理解毫无问题，很简单，其实解的过程难的很，真的太难了。
 * 参考：https://leetcode-cn.com/problems/maximal-square/solution/
 * li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
 * 同时参考官方题解的评论：
 * 状态：这个定义是整道题最关键的。dp[i][j]表示左上角的矩阵范围内以dp[i][j]作为
 * 右下角的正方形的最大边长,注意这里很难想通，必须是以dp[i][j]作为右下角包含进去的
 * 正方形，这里容易想错，不能认为dp[i][j]表示以[i][j]为右下角的界限，包含的所有正方形
 * 的最大值。因为实际上这里如果[i][j]为0，那么dp[i][j]=0而不是等于dp[i-1][j-1]或者其他
 * 什么数学归纳法做出来的值，这点非常容易出错，所以还需要一个maxSide来求所有dp[][]
 * 中最大的那个dp[][].
 * 边界条件： 原数组中0的位置显然不能参与到由“1”组成的正方形中，所以对应的动态
 * 规划数组中这些位置也全都是0，而原数组中1的位置至少是一个由它自己独自构成的
 * 边长为1的正方形，所以，动态规划的边界条件，第一行和第一列直接抄写原数组。
 * 状态转移方程： 基本思路是，当填入了dp[i][j]后正好构成了一个以[i][j]为右下角的
 * 大正方形，dp[i][j]=原正方形边长+1。换句话说，我们要找的是当前位置上正好缺了
 * 右下角的最大正方形的边长，然后dp[i][j]就是这个边长+1。
 * 而我们已经知道了左上角的那一点构成的最大正方形边长（d[i-1][j-1]），左侧那一点
 * 构成的最大正方形边长（d[i][j-1]），以及上方那一点构成的最大正方形边长（d[i-1][j]），
 * 只有三者中的最小值可以和d[i][j]构成完整的正方形，因此动态转移方程是：
 * d[i][j]=min(d[i-1][j],d[i][j-1],d[i-1][j-1])+1
 */
public class DPmaximalSquare221LeetCode {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int [][] dp = new int[matrix.length][matrix[0].length];
        int maxSide=0;
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                //初始化第0行和第0列
                if (i==0||j==0){
                    dp[i][j]=matrix[i][j]-'0';
                    maxSide = Math.max(maxSide,dp[i][j]);
                    continue;
                }
                if (matrix[i][j]=='1'){
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }
        }
        return maxSide*maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix=
                //{{'1'}};
                {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
                };
        System.out.println(new DPmaximalSquare221LeetCode().maximalSquare(matrix));
    }
}
