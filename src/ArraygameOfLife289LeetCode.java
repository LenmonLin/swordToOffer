/**
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的
 * 细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有
 * 一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相
 * 邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状
 * 态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡
 * 是同时发生的。
 * 示例:
 * 输入:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * 进阶:
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先
 * 更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面
 * 板边界时会造成问题。你将如何解决这些问题？
 * @author LemonLin
 * @Description :ArraygameOfLife289LeetCode
 * @date 20.2.10-20:21
 * 思路：注意题目要求的面板上所有格子需要同时被更新。所有很明显是要在遍历完
 * 输入数组前把要改变的状态值必须想办法全部保存起来。第二次遍历输入数组的时候再考虑
 * 全部修改对应的状态。
 * 要改变的状态值怎么保存起来，又不能开辟新数组，只能把原来的细胞情况保存在左移一
 * 位，新的细胞情况保存在右边。总体思路就是用位运算。
 * 上述情况有个bug,不能将新的细胞情况保存在右边，应该保存在左边。旧的细胞情况保存
 * 在右边。因为如果将久的细胞情况保存在左边，新的细胞循环遍历的时候，有的细胞有
 * 两位数（化成二进制来说），有的就一位数，造成程序上的混乱。调了一个小时的bug了。
 * 既然新的细胞情况放在左边，所以存入的时候需要左移<<,自然取出的时候需要右移>>
 */
public class ArraygameOfLife289LeetCode {
    public int [][] gameOfLife(int[][] board) {
        int rows= board.length;
        int colums = board[0].length;
        //第一遍遍历改变状态
        for (int i=0;i<rows;i++){
            for (int j=0;j<colums;j++){
                board[i][j]=board[i][j]+(isLive(board,i,j)<<1);
            }
        }
        //第二遍遍历读出状态
        for (int i=0;i<rows;i++){
            for (int j=0;j<colums;j++){
                board[i][j]=(board[i][j]>>1)&1;
            }
        }
        return board;
    }

    private int isLive(int [][]nums,int i,int j){
        /**
         *  [0,1,0], (i-1,j-1),  (i-1,j),  (i-1,j+1)
         * [0,0,1], (i,j-1),      (i,j),     (i,j+1)
         * [1,1,1], (i+1,j-1), (i+1,j), (i+1,j+1)
         */
        int result =0;
        //记录周围活细胞个数
        int count =0;
        //先把八个位置存起来，可以使程序简洁很多
        int[][] location ={
               {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {0, 1},
                {1, -1},
                {1, 0},
                {1, 1}
        };
        int rows= nums.length-1;
        int columns = nums[0].length-1;
        for (int p=0;p<location.length;p++){
            //前提是要保证边界没有越界
            if (0<=(location[p][0]+i)&&
                    rows>=(location[p][0]+i) &&
                    0<=(location[p][1]+j)&&
                    columns>=(location[p][1]+j) &&
                    (nums[location[p][0]+i][location[p][1]+j]&1)==1){
                count++;
            }
        }
        //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
        if (((nums[i][j]&1)==1)&&count<2){
            result=0;
            //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
        }else if (((nums[i][j]&1)==1)&&(count==2||count==3)){
            result =1;
            //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
        }else if (((nums[i][j]&1)==1)&&count>3){
            result=0;
            //如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
        }else if (((nums[i][j]&1)==0)&&count==3){
            result=1;
        }
        return result;
    }

    public static void main(String[] args) {
        int [][]board ={
                {0,1,0},
        {0,0,1},
        {1,1,1},
        {0,0,0}
        };
        int[][] ints = new ArraygameOfLife289LeetCode().gameOfLife(board);
        int rows= ints.length;
        int colums = ints[0].length;
        for (int i=0;i<rows;i++){
            for (int j=0;j<colums;j++){
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }
}
