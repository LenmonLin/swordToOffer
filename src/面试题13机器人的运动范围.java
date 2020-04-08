/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0]
 * 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能
 * 入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格
 * [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * @author LemonLin
 * @Description :面试题13机器人的运动范围
 * @date 20.4.8-10:07
 * 思路：用dfs或者bfs都可以，主要是边界的判断以及不能大于k和是否访问过的递归出口
 * 的判断
 */
public class 面试题13机器人的运动范围 {
    int result =0;
    public int movingCount(int m, int n, int k) {
        //标记是否访问过
        int [][]visited =new int[m][n];
        bfs(0,0,m,n,k,visited);
        return result;
    }
    private void bfs(int i,int j,int m, int n,int k,int [][] visited){
        if (0>i||i>=m||0>j||j>=n||greaterThanK(i,j,k)||visited[i][j]==1){
            return;
        }
        int [] x ={-1,0,0,1};
        int [] y ={0,-1,1,0};
        visited[i][j]=1;
        result++;
        for (int p=0;p<x.length;p++){
            bfs(i+x[p],j+y[p],m,n,k,visited);
        }
    }
    //是否大于K的判断
    private boolean greaterThanK(int m,int n,int  k){
        int temp =0;
        while (m!=0||n!=0){
            if (m!=0){
                temp += m%10;
                m =m/10;
            }
            if (n!=0){
                temp+=n%10;
                n = n/10;
            }
            if (temp>k){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int m =3;
        int n = 1;
        int k =0;
        System.out.println(new 面试题13机器人的运动范围().movingCount(m, n, k));
//        System.out.println(new 面试题13机器人的运动范围().greaterThanK(m, n, k));
    }
}
