/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂
 * 直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直
 * 的四个方向的‘1’。
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * 思路：这题应该包含两个部分，第一部分DFS，求每个岛屿的面积。同时需要把1标记为0.
 * 第二部分双重循环遍历数组。遍历数组的过程中再使用第一部分的函数。
 * @author LemonLin
 * @Description :DFSmaxAreaOfIsland695LeetCode
 * @date 20.1.15-11:54
 */
public class DFSmaxAreaOfIsland695LeetCode {
    int cnt =0;
    int maxCnt=0;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid==null||grid.length == 0)return 0 ;
        //数组的横向长度(一个一维数组中有几个数)
        int m = grid[0].length;
        //数组的纵向长度（有几个一维数组）
        int n = grid.length;
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (grid[i][j]==1){
                    cnt=0;
                    dfs(grid,i,j);
                }
                if (cnt>maxCnt){
                    maxCnt = cnt;
                }
            }
        }
        return maxCnt;
    }
    public void  dfs(int[][] grid,int p, int q){
        if (p<0||q<0||p>grid.length-1|| q>grid[0].length-1||grid[p][q]==0){
            return;
        }
        grid[p][q]=0;
        cnt++;
        dfs(grid,p+1,q);
        dfs(grid,p,q+1);
        dfs(grid,p-1,q);
        dfs(grid,p,q-1);
    }

    public static void main(String[] args) {
        //预期答案4
        int[][]grid ={
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}};
        System.out.println(new DFSmaxAreaOfIsland695LeetCode().maxAreaOfIsland(grid));
    }
}
