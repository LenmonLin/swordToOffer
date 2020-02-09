/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩
 * 阵来旋转图像。
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 * @author LemonLin
 * @Description :Arrayrotate48LeetCode
 * @date 20.2.9-12:00
 * 思路：需要一个临时变量保存要被替代的数，然后四个数旋转，四个边角的数的下标怎么写？
 * 终止条件范围怎么写？
 * [                     [i][j]     [j][n-i]
                            [1,2,3],
                            [4,5,6],
                            [7,8,9]
                [n-j][i]        [n-i][n-j]
 * ],
 * i<(n+1)/2是关键；
 * 这里i ,j 的下标范围比较难理解，0<= i <(n+1)/2 ,i 是从0 开始，只能到一半，因为
 * i表示行，旋转时行是对称的开始旋转的，第0行开始旋转，第matrix.length-1也开始
 * 旋转。第1行开始旋转，第matrix.length-2也开始旋转，所以这是逐渐趋向中间行的过程
 * 最终的遍历终点也就是中间行。
 * 然后是列变量j，首先列变量起点一定是等于j的，大家可以看做从对角线位置开始每次的
 * 旋转，第一次旋转起点是(0,0),第二次旋转起点是(1,1),以此类推。而列的终点为什么是
 * matrix.length-1-i,可以找规律，当i=0时，最多能遍历到的列是最后一列matrix.length-1-0;
 * 当i=1时，最多能遍历到的列是matrix.length-1-1;（因为最外一层已经遍历了）
 * 当i=2是，最多能遍历到的列是matrix.length-1-1-1，（因为最外两层已经遍历了）
 * 以此类推，所以列每一次遍历，都会想内收缩一层，所以是matrix.length-1-i;
 */
public class Arrayrotate48LeetCode {
    public void rotate(int[][] matrix) {
        int n = matrix.length-1;
        int temp =0;
        for (int i=0;i<(n+1)/2;i++){
            for (int j =i;j<n-i;j++){
                temp = matrix[i][j];
                matrix[i][j]= matrix[n-j][i];
                matrix[n-j][i]=matrix [n-i][n-j];
                matrix [n-i][n-j] = matrix[j][n-i];
                matrix[j][n-i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int [][] matrix ={
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        new Arrayrotate48LeetCode().rotate(matrix);
        int  n = matrix.length-1;
        for (int i =0;i<=n;i++){
            for (int j =0;j<=n;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
