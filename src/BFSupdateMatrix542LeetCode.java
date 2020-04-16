import java.util.LinkedList;
/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * @author LemonLin
 * @Description :BFSupdateMatrix542LeetCode
 * @date 20.4.15-20:35
 * 思路：参考LeetCode1162的思路，多源BFS的考虑。这里和1162不一样的是要设置一个
 * visited[][]数组进行路径访问的标记，LeetCode1162是通过特殊处理在原数组上修改。
 * 参考：https://leetcode-cn.com/problems/01-matrix/solution/2chong-bfs-xiang-
 * jie-dp-bi-xu-miao-dong-by-sweetie/
 * 1、可以通过把原数组中1修改成-1表示没访问过，访问过的非0的数直接写结果层数即可，那么
 * 这样就不需要开辟visited[][]来记录是否访问过了。
 * 2、本题求的是1到0的距离，但是如果用多源BFS的话，需要转换思路求0到1的距离。
 * 显然1到0和0到1的距离是相等的，但是为啥不能求1到0的呢，因为多源BFS来看，如果
 * 入队1，求多源BFS会有问题。求1到0无法用多源。
 * 3、新的matrix[][]等于旧的matrix[][]+1很巧妙，可以想象为水波的外层的层数等于
 * 内一层水波层数+1
 */
public class BFSupdateMatrix542LeetCode {
    public int[][] updateMatrix(int[][] matrix) {
        //先遍历收集多源入队,入队的是坐标元素,一个坐标元素用两个数字，一个数组存储。
        LinkedList<int []> queue = new LinkedList<>();
        for (int i=0;i<matrix.length;i++){
            for (int j =0;j<matrix[0].length;j++){
                if (matrix[i][j]==0){
                    queue.addLast(new int[]{i,j});
                }else {
                    matrix[i][j] =-1;
                }
            }
        }
        int [] x = {-1,1,0,0};
        int [] y = {0,0,-1,1};
        int[] point=null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                point = queue.removeFirst();
                //四个方向的变量
                for (int j=0;j<4;j++){
                    if (0<=point[0]+x[j]&&point[0]+x[j]<matrix.length&&
                            0<=point[1]+y[j]&&point[1]+y[j]<matrix[0].length&&
                            matrix[point[0]+x[j]][point[1]+y[j]]==-1){
                        //这里设计新的matrix[][]等于旧的matrix[][]+1很巧妙，可以想象为
                        //水波的外层的层数等于内一层水波层数+1
                        matrix[point[0]+x[j]][point[1]+y[j]] = matrix[point[0]][point[1]]+1;
                        queue.addLast(new int[]{point[0]+x[j],point[1]+y[j]});
                    }
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int [][] matrix ={
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        int[][] matrix1 = new BFSupdateMatrix542LeetCode().updateMatrix(matrix);
        for (int i=0;i<matrix1.length;i++){
            for (int j =0;j<matrix1[0].length;j++){
                System.out.print(matrix1[i][j]);
            }
            System.out.println();
        }
    }
}
