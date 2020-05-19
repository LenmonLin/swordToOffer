/**
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所
 * 有 1 都更改为 0。在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，
 * 矩阵的得分就是这些数字的总和。返回尽可能高的分数。
 * 示例：
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * 提示：
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 * @author LemonLin
 * @Description :GreedymatrixScore861LeetCode
 * @date 20.5.19-16:37
 * 思路：
 * 先行变换保证第一列全部是1
 * 再列变换保证其他列1比0多
 * bug1:
 * 输入:
 * [[0,1],[1,1]]
 * 输出
 * 4
 * 预期结果
 * 5
 */
public class GreedymatrixScore861LeetCode {
    public int matrixScore(int[][] A) {
        //行数
        int rows= A.length;
        //列数
        int columns = A[0].length;
        //先行变化，保证第一列全是1
        for(int i=0;i<rows;i++){
            if(A[i][0]==0){
                for(int j=0;j<columns;j++){
                    if (A[i][j]==0){
                        A[i][j] =1;
                    }else {
                        A[i][j]=0;
                    }
                }
            }
        }
        //判断列是否0的个数大于1的个数，0的个数多于1的个数，就反转
        boolean change = false;
        for (int j=1;j<columns;j++){
            int count =0;
            for (int i=0;i<rows;i++){
                if (A[i][j]==0){
                    count +=1;
                }
                if (count>rows/2){
                    change = true;
                }
            }
            if (change){
                change = false;
                for (int i=0;i<rows;i++){
                    if (A[i][j]==0){
                        A[i][j] =1;
                    }else {
                        A[i][j] =0;
                    }
                }
            }
        }
        //记录结果
        int result =0;
        for (int i=0;i<rows;i++){
            for (int j=columns-1;j>=0;j--){
                if (A[i][j]==1){
                    //解决bug1：注意这里的下标是从低到高。
                    result +=(int) Math.pow(2,columns-1-j);
                }
            }
        }
        return result ;
    }

    public static void main(String[] args) {
        int [][] input = {
                {0,0,1,1},
                {1,0,1,0},
                {1,1,0,0}
        };
        System.out.println(new GreedymatrixScore861LeetCode().matrixScore(input));
    }
}
