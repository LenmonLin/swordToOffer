import java.util.ArrayList;
/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。
 * 请使用原地算法。
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 * @author LemonLin
 * @Description :ArraysetZeroes73LeetCode
 * @date 20.2.9-21:38
 *  思路：先遍历数组，把元素值是0的元素的横纵坐标放到一个数组，{横坐标，纵坐标}，
 *  所有的元素值是0的元素的横纵坐标放到二维数组{{横坐标1，纵坐标1}，{横坐标2，纵坐
 *  标2}，{横坐标3，纵坐标3}....}
 *  然后再遍历这个二维数组，把输入的数组对应的位置置零。
 *  可能可以优化：
 *  执行用时 :2 ms, 在所有 Java 提交中击败了57.37%的用户
 * 内存消耗 :
 * 49.9 MB, 在所有 Java 提交中击败了5.03%的用户
 */
public class ArraysetZeroes73LeetCode {
    public void setZeroes(int[][] matrix) {
        if (matrix==null||matrix.length==0)return;
        int rows = matrix.length-1;
        int columns = matrix[0].length-1;
        ArrayList<int[]>  arrayList = new ArrayList<int[]>();
        for (int i=0;i<=rows;i++){
            for (int j=0;j<=columns;j++){
                if (matrix[i][j]==0){
                    int[] temp ={i,j};
                    arrayList.add(temp);
                }
            }
        }
        for (int k=0;k<arrayList.size();k++){
            int m = arrayList.get(k)[0];
            int n = arrayList.get(k)[1];
            for (int p = 0;p<=columns;p++){
                matrix[m][p]=0;
            }
            for (int q=0;q<=rows;q++){
                matrix[q][n] =0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix={
                {0,1}
//                {0,1,2,0},
//                {3,4,5,2},
//                {1,3,1,5}
//                {1,1,1},
//                {1,0,1},
//                {1,1,1}
        };
        new ArraysetZeroes73LeetCode().setZeroes(matrix);
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
