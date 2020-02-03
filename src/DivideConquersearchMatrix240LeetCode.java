/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * @author LemonLin
 * @Description :DivideConquersearchMatrix240LeetCode
 * @date 20.2.3-16:04
 * 思路:设row代表matrix行，column代表matrix的列，从matrix的右上角元素开始比较，
 * 如果target比matrix右上角的元素小，说明右上角这一列都比target大，column左移。
 * 如果target比matrix右上角的元素大，说明右上角这一行都比target小，row下移。
 */
public class DivideConquersearchMatrix240LeetCode {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null||matrix.length==0) return false;
        boolean result = false;
        int row=0;
        int column = matrix[0].length-1;
        while (true){
            if (row>matrix.length-1||column<0){
                break;
            }
            if (matrix[row][column]>target){
                column--;
                continue;
            }
            if (matrix[row][column]<target){
                row++;
                continue;
            }
            if (matrix[row][column]==target){
                result = true;
                break;
            }
        }
        return result;
    }

}
