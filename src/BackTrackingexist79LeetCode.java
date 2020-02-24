/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水
 * 平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * 思路：比较直白，选择常规回溯，向四个方向递归即可。返回结果是Boolean这个不好操作，
 * 和返回ArrayList做区别。
 * 运行超时案例：
 * [["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],
 * ["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a",
 * "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","b"]]
 * "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 * aaaaaa"
 *超时解决方案：
 * https://leetcode-cn.com/problems/word-search/solution/
 * de-acjing-li-zong-jie-yi-xia-zhe-ge-you-de-dfshui-/
 * @author LemonLin
 * @Description :BackTrackingexist79LeetCode
 * @date 20.1.21-11:48
 * 思路：参考：https://leetcode-cn.com/problems/word-search/solution/
 * hui-su-dfs-by-powcai/
 * bug1:
 * 输入:
 * [["a","b"],["c","d"]]
 * "abcd"
 * 输出
 * true
 * 预期结果
 * false
 * 思考：这题看似简单，其实蕴含了很多细节写法不能出错，这里的难点主要是，是判断
 * true和false，所以DFS的函数返回值是Boolean类型，所以比较不习惯怎么操作。
 */
public class BackTrackingexist79LeetCode {

    public boolean exist(char[][] board, String word) {
        if(board[0].length * board.length < word.length()) return false;
        boolean [][] isUsed= new boolean[board.length][board[0].length];
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                //这个if的写法非常关键，仔细体会,先用word.charAt(0)==board[i][j]可以达到
                //剪枝的效果。
                if(word.charAt(0)==board[i][j]&&backTracking(board,i,j,word,
                        0,isUsed)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean backTracking(char[][] board,int x,int y,String word,int index,boolean [][] isUsed){
        if (index ==word.length()){
            return true;
        }
        if (x<0||y<0||y>board[0].length-1||x>board.length-1||
                index>word.length()-1||
                word.charAt(index)!=board[x][y]||isUsed[x][y]==true){
            return false;
        }
        isUsed[x][y]=true;
        if (backTracking(board,x+1,y,word,index+1,isUsed)||
                backTracking(board,x,y+1,word,index+1,isUsed)||
                backTracking(board,x-1,y,word,index+1,isUsed)||
                backTracking(board,x,y-1,word,index+1,isUsed)){
            return true;
        }
        isUsed[x][y]=false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board =
                {{'a','b'},
                {'c','d'}};
//                {{'C','A','A'},
//                {'A','A','A'},
//                {'B','C','D'}};
        String word =
                "abcd";
//                "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(new BackTrackingexist79LeetCode().exist(board, word));
    }
}
