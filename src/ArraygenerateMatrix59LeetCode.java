/**
 * 给定一个正整数 n，生成一个包含 1 到 n 所有元素，且元素按顺时针顺序螺旋排列的
 * 正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * @author LemonLin
 * @Description :ArraygenerateMatrix59LeetCode
 * @date 20.2.9-22:06
 * 思路:和LeetCode54类似
 */
public class ArraygenerateMatrix59LeetCode {
    public int[][] generateMatrix(int n) {
        if (n<=0)return new int[0][];
//        int rows = 0;
//        for (int i =1;i<=n;i++){
//            if (i*i>=n){
//                rows =i;
//                break;
//            }
//        }
        int rowsStart=0;
        int rowsEnd=n-1;
        int columnsStart =0;
        int columnsEnd =n-1;
        int [][] result = new int[n][n];
        int digits=1;
        while (rowsStart<=rowsEnd&&columnsStart<=columnsEnd){
            if (rowsStart<=rowsEnd){
                for (int j = columnsStart;j<=columnsEnd;j++){
                    result[rowsStart][j]=digits;
                    digits++;
                }
                rowsStart++;
            }
            if (columnsStart<=columnsEnd){
                for (int i=rowsStart;i<=rowsEnd;i++){
                    result[i][columnsEnd]=digits;
                    digits++;
                }
                columnsEnd--;
            }
            if (rowsStart<=rowsEnd){
                for (int j = columnsEnd;j>=columnsStart;j--){
                    result[rowsEnd][j]=digits;
                    digits++;
                }
                rowsEnd--;
            }
            if (columnsStart<=columnsEnd){
                for (int i=rowsEnd;i>=rowsStart;i--){
                    result[i][columnsStart]=digits;
                    digits++;
                }
                columnsStart++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n=1;
        int [][] matrix = new ArraygenerateMatrix59LeetCode().generateMatrix(n);
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
//        int [][] test = new int[2][2];
//        test[0][0]=0;
//        System.out.println(test[0][0]);
    }
}
