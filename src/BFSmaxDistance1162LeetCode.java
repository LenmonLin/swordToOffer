import java.util.LinkedList;

/**
 *
*  你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）
 *  都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋
 *  区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
*  我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1)
 *  这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
*  如果我们的地图上只有陆地或者海洋，请返回 -1。
*  示例 1：
 *  图片：https://leetcode-cn.com/problems/as-far-from-land-as-possible/
*  输入：[[1,0,1],[0,0,0],[1,0,1]]
*  输出：2
*  解释：
*  海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
*  示例 2：
 *  图片：https://leetcode-cn.com/problems/as-far-from-land-as-possible/
*  输入：[[1,0,0],[0,0,0],[0,0,0]]
*  输出：4
*  解释：
*  海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
*  提示：
*  1 <= grid.length == grid[0].length <= 100
*  grid[i][j] 不是 0 就是 1
 * @author LemonLin
 * @Description :BFSmaxDistance1162LeetCode
 * @date 20.3.29-11:34
 * 思路：
 * 参考：https://leetcode-cn.com/problems/as-far-from-land-as-possible/
 * solution/jian-dan-java-miao-dong-tu-de-bfs-by-sweetiee/
 * https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/
 * li-qing-si-lu-wei-shi-yao-yong-bfs-ru-he-xie-bfs-d/
 * 这里先解释一下题意：
 * 1、曼哈顿距离：其实就是在网格中需要走的步数。
 * 2、距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域
 * 的距离。
 * 这里出现了最远和最近，感觉好像矛盾，其实说的是一个意思。参考系不一样：距离陆地
 * 区域最远的海洋区域，是以陆地为出发点，所能到达的最远的海洋区域，可以理解为以
 * 陆地为出发点，到达这个最远海洋区域旁边的陆地或者边界的最短距离。这样两种描述就
 * 统一了。海洋区域到离它最近的陆地区域的距离，就是以海洋区域为参照物，为出发点，向
 * 陆地进攻，至于为什么BFS可以求最短路径，可以参考LeetCode127的介绍。
 * 3、本题求得是一个多源同时开始向海洋遍历的方式，
 * 对于图的BFS与Tree的BFS区别如下：
 * A、tree只有1个root，而图可以有多个源点，所以首先需要把多个源点都入队。
 * B、tree是有向的因此不需要标志是否访问过，而对于无向图来说，必须得标志是否访问过！
 * 并且为了防止某个节点多次入队，需要在入队之前就将其设置成已访问。
 * 4、解决如何多源同时遍历，可以利用BFS的代码特性，把多源全部先入队，这样多源就
 * 变成层次遍历的同一层节点，虽然不是严格意义上的同时(同一时刻)开始遍历，但是最终
 * 是达到了同时遍历的效果，因为层次遍历需要对本层节点处理完才能继续处理下一层节点。
 * 5、本题的标记可以通过把原数组的0，设置为遍历深度的数字，这样就是遍历过的节点
 * 就是非0数组。不用开辟额外数组空间标记是否遍历过。很巧妙。
 * bug1:
* 输入:
* [[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1]]
* 输出
* 0
* 预期结果
* -1
 */
public class BFSmaxDistance1162LeetCode {
    public int maxDistance(int[][] grid) {
        //先遍历收集多源入队,入队的是坐标元素,一个坐标元素用两个数字，一个数组存储。
        LinkedList<int []> queue = new LinkedList<>();
        for (int i=0;i<grid.length;i++){
            for (int j =0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    queue.addLast(new int[]{i,j});
                }
            }
        }
        int [] x = {-1,1,0,0};
        int [] y = {0,0,-1,1};
        int[] temp=null;
        boolean hasOcean = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
               temp = queue.removeFirst();
               //四个方向的变量
                for (int j=0;j<4;j++){
                    if (0<=temp[0]+x[j]&&temp[0]+x[j]<grid.length&&
                            0<=temp[1]+y[j]&&temp[1]+y[j]<grid[0].length&&
                            grid[temp[0]+x[j]][temp[1]+y[j]]==0){
                        hasOcean = true;
                        queue.addLast(new int[]{temp[0]+x[j],temp[1]+y[j]});
                        //主要本题记录层数的方法很特殊，直接赋值数组元素中
                        grid[temp[0]+x[j]][temp[1]+y[j]]=grid[temp[0]][temp[1]]+1;
                    }
                }
            }
        }
        //解决bug1
        if (!hasOcean){
            return -1;
        }
        return grid[temp[0]][temp[1]]-1;
    }

    public static void main(String[] args) {
        int [][] grid ={
                {1,0,1},{0,0,0},{1,0,1}
        };
        System.out.println(new BFSmaxDistance1162LeetCode().maxDistance(grid));
    }
}
