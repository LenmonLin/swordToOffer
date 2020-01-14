/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
* 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
* 示例:
* X X X X
* X O O X
* X X O X
* X O X X
* 运行你的函数后，矩阵变为：
* X X X X
* X X X X
* X X X X
* X O X X
* 解释:
* 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元
 * 素在水平或垂直方向相邻，则称它们是“相连”的。
 * 思路：没有思路。。。脑袋里只有DFS，但是具体怎么操作毫无头绪。
 * 看了题解：理解如下：分为两个步骤。
 * 第一步：先处理边界的O，把边界的O以及和边界的O相连的O都替换成不同于O与X的中
 * 间过渡字符C，用什么方法，二重for循环遍历边界值，然后再结合dfs递归处理。
 * 第二步：重新二重for循环遍历第一步修改成C的数组，然后把所有的O改成X，把所有
 * 的C改成O。完成任务。
 * 总体的图示如下：
 * X X X X         X X X X         X X X X
 * X O O X  --->X O O X  --->X X X X
 * X X O X         X X O X         X X X X
 * X O X X         X C X X         X O X X
 * 这里细节上要注意：
 * 1、dfs中的边界判断要进行p<0||q<0||p>board.length-1||q>board[0].length-1
 * 2、边界判断上p是>board.length-1不是board[0].length-1，容易想错，注意一下
 * 3、p<0||q<0||p>board.length-1||q>board[0].length-1要放在board[p][q]=='C'
 * ||board[p][q]=='X'前面，否则会发生数组越界
 * 4、特殊情况判断if (board==null||board.length == 0)return ;容易想不到
 * @author LemonLin
 * @Description :DFSsolve130LeetCode
 * @date 2020/1/11-10:25
 */
public class DFSsolve130LeetCode {
    public void solve(char[][] board) {
        if (board==null||board.length == 0)return ;
        //数组的横向长度(一个一维数组中有几个数)
        int m = board[0].length;
        //数组的纵向长度（有几个一维数组）
        int n = board.length;
        //第一步双循环处理边界O
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (i==0||i==n-1||j==0||j==m-1){
                    dfs(board,i,j);
                }
            }
        }
        //第二步双循环，把O变成X，把C恢复O
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
              if(board[i][j]=='O'){
                  board[i][j]='X';
              }
                if(board[i][j]=='C'){
                    board[i][j]='O';
                }
            }
        }
    }
    public void dfs(char[][] board,int p,int q){
        //如果已经等于C，就说明修改过，就不需要修改
        if (p<0||q<0||p>board.length-1||
                q>board[0].length-1||board[p][q]=='C'||board[p][q]=='X'){
            return;
        }
        board[p][q]='C';
        //通过四个方向的深度优先搜索，寻找边界O
        dfs(board,p+1,q);
        dfs(board,p,q+1);
        dfs(board,p-1,q);
        dfs(board,p,q-1);
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'}};

    }
}
