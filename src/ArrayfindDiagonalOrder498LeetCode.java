/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩
 * 阵中的所有元素，对角线遍历如下图所示。
 * 示例:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * 解释:
 *图片：https://leetcode-cn.com/classic/problems/diagonal-traverse/description/
 * 说明:
 * 给定矩阵中的元素总数不会超过 100000 。
 * @author LemonLin
 * @Description :ArrayfindDiagonalOrder498LeetCode
 * @date 20.2.19-10:47
 * 思路：观察横纵坐标的规律，
 * [
 *  [[0,0],[0,1] ,[0,2]],
 *  [ [1,0],[1,1] ,[1,2]],
 *  [[2,0],[2,1] ,[2,2]]
 * ]
 * 可以发现:题目有两个方向的遍历：
 * 设置横坐标为i，纵坐标为j
 * 1、up，从左下角到右上角：这个时候坐标变换规律：i--;j++;
 * 2、down，从右上角到左下角：这个时候坐标变换规律：i++;j--;
 * 转换规律:
 * 从up转换到down:i不变，j++;但是如果j越界了，就变成i++,j不变。
 *从down转换到up:i++,j不变；但是如果i越界了，就变成i不变,j++不变。
 * 边界规律：
 * i+j从0开始定增。
 */
public class ArrayfindDiagonalOrder498LeetCode {
    int i =0;
    int j =0;
    int resultIndex =0;
    int count =0;
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix==null||matrix.length==0)return new int[0];
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] result = new int[rows*columns];
        //[0,0]的状态
        up(matrix,result,rows,columns);
        //count 记录是第几次斜线。总的斜线范围到rows+columns-1
        count++;
        while (count<=rows+columns-1){
            if(count%2==0){
                //如果没有越界就i++,j不变，如果越界了就i不变，j++
                if (i<rows-1){
                    i++;
                }else {
                    j++;
                }
                up(matrix,result,rows,columns);
            }else {
                if (j<columns-1){
                    j++;
                }else {
                    i++;
                }
                down(matrix,result,rows,columns);
            }
            count++;
        }
        return result;
    }
    private void up(int [][]matrix,int[] result,int rows,int columns){
        while (i>=0&&i<rows&&j>=0&&j<columns&&i+j==count){
            result[resultIndex] = matrix[i][j];
            i--;
            j++;
            resultIndex++;
        }
        //这里while跳出循环是因为不满足边界，因为下次i和j还要接着用，所以要恢复一下
        i++;
        j--;
    }
    private void down(int [][]matrix,int[] result,int rows,int columns){
        while (i>=0&&i<rows&&j>=0&&j<columns&&i+j==count){
            result[resultIndex] = matrix[i][j];
            i++;
            j--;
            resultIndex++;
        }
        i--;
        j++;
    }

    public static void main(String[] args) {
        int [][] matrix={
//                {1, 2, 3 },
//                {4, 5, 6 },
//                {7, 8, 9 }
                };
        int[] result = new ArrayfindDiagonalOrder498LeetCode().findDiagonalOrder(matrix);
        for (int i :result){
            System.out.println(i);
        }
    }
}
