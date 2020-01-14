/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水
 * 包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的
 * 四个边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 思路：两部分组成，第一部分是深度优先搜索，每个局部都采取深度优先搜索。
 * 第二步是全局的整个数组的遍历，这题其实有点像图的题目，整个数组的遍历使用两个
 * for循环，最里面for循环进行边界条件判断之后调用深度优先搜索。
 * 这里有个想法觉得，大致了解深度优先搜索的套路框架，有点明白树的先序遍历其实是
 * 图的深度优先搜索的一种形式。品一品，细品还是有点味道。
 * dfs(Treenode root ){
 * 这是树的遍历
 *     dfs(root.left);
 *     dfs(root.right);
 *如果是矩阵的，可能需要左上右下四周遍历一下。
 *     dfs(root 左边);
 *     dfs(root 上边);
 *     dfs(root 右边);
 *     dfs(root 下边);
 * }
 * 细节疑问：怎么记录岛屿的数量？
 * 解：dfs遍历过程中，要把1改成0，然后二重循环就可以通过数组值是否为1记录进入dfs
 * 的次数，也就是岛屿的数量
 * @author LemonLin
 * @Description :DFSnumIslands200LeetCode
 * @date 2020/1/11-21:39
 */
public class DFSnumIslands200LeetCode {
    public int numIslands(char[][] grid) {
        if (grid==null||grid.length == 0)return 0 ;
        //数组的横向长度(一个一维数组中有几个数)
        int m = grid[0].length;
        //数组的纵向长度（有几个一维数组）
        int n = grid.length;
        //记录岛屿数量
        int result =0;
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (grid[i][j]=='1'){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }
    public void dfs(char[][] grid,int p,int q){
        if (p<0||q<0||p>grid.length-1|| q>grid[0].length-1||grid[p][q]=='0'){
            return;
        }
        grid[p][q]='0';
        dfs(grid,p+1,q);
        dfs(grid,p,q+1);
        dfs(grid,p-1,q);
        dfs(grid,p,q-1);
    }
}
