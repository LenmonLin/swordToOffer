/**
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）
 * 和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，
 * 小写字符表示黑棋。
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝
 * 那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色
 * 相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 * 返回车能够在一次移动中捕获到的卒的数量。
 * @author LemonLin
 * @Description :ArraynumRookCaptures999LeetCode
 * @date 20.3.26-17:32
 * 示例 1：
 *图片：https://leetcode-cn.com/problems/available-captures-for-rook/
 * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
 * [".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：3
 * 解释：
 * 在本例中，车能够捕获所有的卒。
 * 示例 2：
 *图片：https://leetcode-cn.com/problems/available-captures-for-rook/
 * 输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],
 * [".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],
 * [".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：0
 * 解释：
 * 象阻止了车捕获任何卒。
 * 示例 3：
 *图片：https://leetcode-cn.com/problems/available-captures-for-rook/
 * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
 * [".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],
 * [".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],
 * [".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：3
 * 解释：
 * 车可以捕获位置 b5，d6 和 f5 的卒。
 * 提示：
 * board.length == board[i].length == 8
 * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
 * 只有一个格子上存在 board[i][j] == 'R'
 * 思路：遍历到有车的地方，然后向四周方向放射，看看能不能满足条件。
 */
public class ArraynumRookCaptures999LeetCode {
    public int numRookCaptures(char[][] board) {
        int result =0;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                if (board[i][j]=='R'){
                     result+= helper(result, i, j, board);
                }
            }
        }
        return  result;
    }
    public int helper(int result, int i,int j ,char[][] board){
        int tempi = i;
        int tempj= j;
        int[][] destination ={{-1,0},{1,0},{0,-1},{0,1}};
        for (int k=0;k<destination.length;k++){
            //判断下标是不是在区间内
            while (0<=tempi+destination[k][0]&&
                    tempi+destination[k][0]<board.length&&
                    0<=tempj+destination[k][1]&&
                    tempj+destination[k][1]<board[tempi].length){
                //如果是'.',更新下标继续循环。
                if (board[tempi+destination[k][0]][tempj+destination[k][1]]=='.'){
                    tempi = tempi+destination[k][0];
                    tempj = tempj+destination[k][1];
                    continue;
                }
                //如果是'R','B',就停止了，这条方向不能再继续遍历
                if (board[tempi+destination[k][0]][tempj+destination[k][1]]=='R'||
                        board[tempi+destination[k][0]][tempj+destination[k][1]]=='B'){
                    break;
                }
                //如果是'p',计数加一，同时停止，退出这条方向。
                if(board[tempi+destination[k][0]][tempj+destination[k][1]]=='p'){
                    result++;
                    break;
                }
            }
            //因为需要四个方向反射，所以需要记录下坐标值，以便下个方向使用
            tempi = i;
            tempj= j;
        }
        return  result;
    }

    public static void main(String[] args) {
        char [][] board ={
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','R','.','.','.','p'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}
        };
        System.out.println(new ArraynumRookCaptures999LeetCode().numRookCaptures(board));
    }
}
