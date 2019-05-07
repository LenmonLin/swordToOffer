import java.util.ArrayList;
/**
 * 题目描述
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * 1   2   3   4
 * 5   6   7   8
 * 9  10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *  解题要点：
 *      这道题的求解没有涉及到复杂的数据结构或高级解法，就是一个判断数组边界的问题
 *      打印第一圈的左上角坐标是(0,0),第二圈的左上角坐标是（1,1），以此类推。我们注意到，左上角的坐标中行标和
 * 列标总是相同的，于是可以在矩阵中选取左上角（start,start）的一圈作为我们分析的目标。
 *      对一个5*5的矩阵而言，最后一圈只有一个数字，对应的坐标为（2,2）。我们发现5>2*2。对于一个6*6的矩阵而
 * 言，最后一圈有4个数字，其左上角的坐标仍然为（2,2）。我们发现6>2*2依然成立。所以得出整个数据的循环
 * 条件是：columns>startX*2;rows>startY*2;
 *  如果输入的参数和你需要的参数不一样可以用函数嵌套函数，这样可以实现参数的转换
 * 1、发现不知道以什么为起始点：以左上角的坐标为起点;
 * 2、需要计算右上角的列endX=column-1-start;
 *       和右下角的行endY=rows-1-start;
 * 3、总共打印分为四步，
 *      a：从左到右
 *      b:从上到下：如果只有一行，那么就不需要第二步了，所以循环条件是：末端列号>起始列号   endY>start
 *      c:从右到左: 需要第三步的前提条件是圈内至少有两行两列，所以循环条件是：在b步下，还要末端行号>起始行
 *      号， endX>start
 *      d:从下到上:需要第四步的前提条件是至少有三行两列，所以需要在第c步的条件下，必须满足，末端列号>=起始列
 *      好+2
 * 解题需要知识：
 * java 如何获取一个2维数组的长和宽？
 * 如一定义二维数组array[][],则获得该数组的长度（即行数）的代码为array.length，
 * 对于每一行的宽度可以循环获得，如获得第i行的宽度(i的范围是0~array.length-1)的代码
 * 为array[i].length。
 * @author LemonLin
 * @Description :PrintMatrix20
 * @date 2018/3/7-16:52
 */
public class ArrayPrintMatrix20 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int col = matrix.length;//列数
        int row= matrix[0].length;//行数
        if (matrix==null||col<=0||row<=0){
            return null;
        }
        int start=0;
        while (start*2<col&&start*2<row){
            PrintMatrixInCircle(matrix,col,row,start,arrayList);
            //一定要记得start要++
            start++;
        }
        return arrayList;
    }
    public void PrintMatrixInCircle(int [][] matrix,int columns,int rows,int start,ArrayList<Integer> arrayList){
        int endX=rows-1-start;
        int endY=columns-1-start;
//      第一步总要执行
        for (int i =start;i<=endX;++i){
            arrayList.add(matrix[start][i]);
        }
//        第二步需要判断末尾列号大于起始列号
        if (start<endY){
            for (int j=start+1;j<=endY;++j){
                arrayList.add(matrix[j][endX]);
            }
        }
//      第三步需要在第二步的基础之上
        if (start<endY&&start<endX){
            for (int i = endX-1;i>=start;--i){
                arrayList.add(matrix[endY][i]);
            }
        }
//      第四步需要在第三部的基础之上
        if (start+1<endY&&start<endX){
            for (int j=endY-1;j>start;--j){
                arrayList.add(matrix[j][start]);
            }
        }
    }
    //测试代码
    public static void main(String[] args) {
        int [][] text={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] test1 ={{1},{2},{3},{4},{5}};

        System.out.println("二维数组的列的个数"+text.length);
        System.out.println("二维数组的行的个数"+text[0].length);

        ArrayPrintMatrix20 arrayPrintMatrix20 = new ArrayPrintMatrix20();
        ArrayList<Integer> arrayList = arrayPrintMatrix20.printMatrix(text);
        for (int i =0;i<=arrayList.size()-1;++i){
            System.out.println(arrayList.get(i));
        }
        System.out.println("二维数组的列的个数"+test1.length);
        System.out.println("二维数组的行的个数"+test1[0].length);
    }
}
