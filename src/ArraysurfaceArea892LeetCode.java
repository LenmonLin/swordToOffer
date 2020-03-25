/**
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * 请你返回最终形体的表面积。
 * 示例 1：
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 * 提示：
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 * @author LemonLin
 * @Description :ArraysurfaceArea892LeetCode
 * @date 20.3.25-11:39
 * 思路：首先计算每个点坐标独立放V个正方体的时候的表面积，(1,6),(2,10),(3,14)...观察出
 * 是等差数列。an=a0+(n-1)4 。
 * 其次计算每个点坐标和周围四个方向的正方体重合时可能被遮住的表面积。
 * 最后两者相减即为所求结果。
 */
public class ArraysurfaceArea892LeetCode {
    public int surfaceArea(int[][] grid) {
        int result =0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]!=0){
                    //计算每个点坐标独立放V个正方体的时候的表面积，
                    int temp = 6+(grid[i][j]-1)*4;
                    result+=temp;
                    //其次计算每个点坐标和周围四个方向的正方体重合时可能被遮住的表面积。
                    //这里同时进行相减的操作
                    result-=shieldedArea(i,j,grid);
                }
            }
        }
        return result;
    }
    //计算遮挡面积
    private int shieldedArea(int x,int y,int[][] grid){
        int output = 0;
        int[][] around ={{-1,0},{1,0},{0,-1},{0,1}};
        for (int i=0;i<around.length;i++){
            //先判断不能出界，再判断四周的点不等于0才可
            if (0<=x+around[i][0]&&x+around[i][0]<grid.length&&
                    0<=y+around[i][1]&&y+around[i][1]<grid[x].length&&
                    grid[x+around[i][0]][y+around[i][1]]!=0){
                //计算每个点坐标和周围四个方向的正方体重合时可能被遮住的表面积，取最小值
                output+=Math.min(grid[x][y],grid[x+around[i][0]][y+around[i][1]]);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int [][]grid ={{1,0},{0,2}};
        System.out.println(new ArraysurfaceArea892LeetCode().surfaceArea(grid));
    }
}
