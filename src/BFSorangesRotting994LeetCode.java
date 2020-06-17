import java.util.LinkedList;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * 示例 1：
 *图片：https://leetcode-cn.com/classic/problems/rotting-oranges/description/
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * 提示：
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 * @author LemonLin
 * @Description :BFSorangesRotting994LeetCode
 * @date 20.3.4-10:07
 * 思路：使用BFS 遍历，到最后遍历结束记录最小层数。应该二重循环数组然后再BFS遍历，
 * 因为每个节点都有可能是根节点。BFS需要把1改成2。
 * 第二轮二重循环看看还没有1，如果有新鲜的就返回-1.
 * 思路错误：
 * 1、认为遍历从不同的值为2的边界开始BFS，然后取最小的那个，就是结果。其实每个
 * 值为2的节点都能同时传染值为1的节点。没有想到这个同时性。
 * 所以怎么解决同时开始BFS。
 * bug1：
 * 输入:
 * [[1,2,1,1,2,1,1]]
 * 输出
 * 4
 * 预期结果
 * 2
 * bug2:
 * 输入:
 * [[2,2,2,1,1]]
 * 输出
 * 3
 * 预期结果
 * 2
 * bug3:
 * 输入:
 * [[0]]
 * 输出
 * -1
 * 预期结果
 * 0
 * bug4:
 * 输入:
 * [[1]]
 * 输出
 * 0
 * 预期结果
 * -1
 * 参考：https://leetcode-cn.com/problems/rotting-oranges/solution/li-qing-si-lu-wei-shi-yao-yong-bfsyi-ji-ru-he-xie-/
 * 本题和LeetCode1162差不多，考察的是多源BFS求最短路径的问题。
 * 本题还有一个思考点，是怎么确定遍历结束后是否还有不能被腐烂的橘子，有两种方法：
 * 第一种，重新遍历一遍修改之后的数组，是否存在有1的元素即可，
 * 第二种：最开始寻找多源腐烂橘子时，顺便记录新鲜橘子的数量。在 BFS 中，每遍历到
 * 一个橘子（污染了一个橘子），就将新鲜橘子的数量减一。如果 BFS 结束后这个数量仍
 * 未减为零，说明存在无法被污染的橘子。
 */
public class BFSorangesRotting994LeetCode {
    class  Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x =x;
            this.y = y;
        }
    }
    int result =-1;
    public int orangesRotting(int[][] grid) {
        boolean flag =false;
        if (grid.length==0)return 0;
        int rows = grid.length;
        int colums = grid[0].length;
        LinkedList<Node> queue = new LinkedList<>();
        //多源BFS，先把所有腐烂的橘子入队。
        for (int i =0;i<rows;i++){
            for (int j=0;j<colums;j++){
               if (grid[i][j]==2){
                   flag= true;
                   Node node = new Node(i,j);
                   queue.addLast(node);
               }
            }
        }

        while (!queue.isEmpty()){
            int size = queue.size();
            result++;
            for (int i=0;i<size;i++){
                Node tempNode = queue.removeFirst();
                int tempX =tempNode.x;
                int tempY = tempNode.y;
                if (tempX-1>=0&&grid[tempX-1][tempY]==1){
                    queue.addLast(new Node(tempX-1,tempY));
                    grid[tempX-1][tempY] = 2;
                }
                if (tempX+1<rows&&grid[tempX+1][tempY]==1){
                    queue.addLast(new Node(tempX+1,tempY));
                    grid[tempX+1][tempY] = 2;
                }
                if (tempY-1>=0&&grid[tempX][tempY-1]==1){
                    queue.addLast(new Node(tempX,tempY-1));
                    grid[tempX][tempY-1] = 2;
                }
                if (tempY+1<colums&&grid[tempX][tempY+1]==1){
                    queue.addLast(new Node(tempX,tempY+1));
                    grid[tempX][tempY+1] = 2;
                }
            }
        }
        //遍历修改后的数组，如果还存在1，说明无法腐烂，返回结果-1
        for (int i =0;i<rows;i++){
            for (int j=0;j<colums;j++){
                if (grid[i][j]==1){
                    flag = true;
                    result=-1;
                    break;
                }
            }
        }
        if (!flag){
            return 0;
        }
        return result;
    }
    public static void main(String[] args) {
        int [][] grid ={
                {2,1,1},
                {1,1,0},
                {0,1,1}

//                {2,1,1},
//                {0,1,1},
//                {1,0,1}

//                {0,2}

//                {1,2,1,1,2,1,1}

//            {2,2,2,1,1}
        };
        System.out.println(new BFSorangesRotting994LeetCode().orangesRotting(grid));
    }
}
