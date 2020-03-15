import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵
 * 中的所有元素。
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * @author LemonLin
 * @Description :ArrayspiralOrder54LeetCode
 * @date 20.2.9-17:06
 * 思路：当遍历完第一行时，把遍历范围第一行去掉，接着遍历最后一列，遍历完了之后
 * 再把最后一列去掉，如此循环，就是每次遍历结束都要更新遍历访问范围。
 * bug1：
 * []
 * java.lang.ArrayIndexOutOfBoundsException: 0
 */
public class ArrayspiralOrder54LeetCode {
    public List<Integer> spiralOrder(int[][] matrix) {
        //解决bug1:
        if (matrix==null||matrix.length==0)return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        int rowsStart=0;
        int rowsEnd=matrix.length-1;
        int columnsStart =0;
        int columnsEnd =matrix[0].length-1;
        while (rowsStart<=rowsEnd&&columnsStart<=columnsEnd){
            if (rowsStart<=rowsEnd){
                for (int j = columnsStart;j<=columnsEnd;j++){
                    result.add(matrix[rowsStart][j]);
                }
                rowsStart++;
            }
            if (columnsStart<=columnsEnd){
                for (int i=rowsStart;i<=rowsEnd;i++){
                    result.add(matrix[i][columnsEnd]);
                }
                columnsEnd--;
            }
            if (rowsStart<=rowsEnd){
                for (int j = columnsEnd;j>=columnsStart;j--){
                    result.add(matrix[rowsEnd][j]);
                }
                rowsEnd--;
            }
            if (columnsStart<=columnsEnd){
                for (int i=rowsEnd;i>=rowsStart;i--){
                    result.add(matrix[i][columnsStart]);
                }
                columnsStart++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix={
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9,10,11,12}
//                {}
//                { 1, 2, 3 },
//                { 4, 5, 6 },
//                { 7, 8, 9 }
        };
        List<Integer> integers = new ArrayspiralOrder54LeetCode().spiralOrder(matrix);
        for(int i =0;i<integers.size();i++){
            System.out.print(integers.get(i));
        }
    }
}
