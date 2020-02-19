import java.util.HashMap;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 上图是一个部分填充的有效的数独。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 示例 1:
 * 输入:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 * 输入:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * @author LemonLin
 * @Description :HashMapisValidSudoku36LeetCode
 * @date 20.2.18-17:06
 * 思路：参考：https://leetcode-cn.com/problems/valid-sudoku/solution/
 * you-xiao-de-shu-du-by-leetcode/
 * 最直白的解法，就是按照题意：
 * 1、横坐标遍历一次看看是否有效
 * 2、纵坐标遍历一次看看是否有效
 * 3、每个9*9小框遍历一次看看是否有效
 * 实际上一次遍历就可以同时记录上述三种情况，只要
 * 每个行设置一个hashmap，
 * 每个列设置一个hashmap
 * 每个9*9小框设置一个hashmap。
 * 这题的难点是数据结构的设计处理，细节上比较不好处理
 * 1、 本题设置：数据类型是hashmap的数组，每个数组元素是hashmap;
 *  行hashmap数组
 *  列hashmap数组
 *  9*9小方格hashmap数组.
 *  2、本题难点是boxes的坐标设置：
 *   boxesIndex = (行坐标 / 3 ) * 3 + 列坐标/ 3;
 */
public class HashMapisValidSudoku36LeetCode {
    public boolean isValidSudoku(char[][] board) {
        //行的hashMap
        HashMap<Integer,Integer> [] rows= new HashMap[9];
        //列的hashMap
        HashMap<Integer,Integer>[] columns = new HashMap[9];
        //9*9小格子的hashMap
        HashMap<Integer,Integer>[] boxes = new HashMap[9];
        //必须初始化，不然下面使用的时候会报NullPointerException
        for (int i=0;i<9;i++){
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                int k =0;
                if (board[i][j]!='.'){
                    k =board[i][j]-'0';
                    int boxesIndex = (i / 3 ) * 3 + j / 3;
                    rows[i].put(k,rows[i].getOrDefault(k,0)+1);
                    columns[j].put(k,columns[j].getOrDefault(k,0)+1);
                    boxes[boxesIndex].put(k,boxes[boxesIndex].getOrDefault(k,0)+1);
                    if (rows[i].get(k) > 1 || columns[j].get(k) > 1 || boxes[boxesIndex].get(k) > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
