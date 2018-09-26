import java.util.ArrayList;

/**
 * java 如何获取一个2维数组的长和宽？
 *
 * 如一定义二维数组array[][],则获得该数组的长度（即行数）的代码为array.length，
 * 对于每一行的宽度可以循环获得，如获得第i行的宽度(i的范围是0~array.length-1)的代码为array[i].length。
 *
 * 解题要点：
 * 0、整个数据的循环条件是：columns>startX*2;rows>startY*2;
 * 如果输入的参数和你需要的参数不一样可以用函数嵌套函数，这样可以实现参数的转换
 * 1、发现不知道以什么为起始点：以左上角的坐标为起点;
 * 2、需要计算右上角的列endX=column-1-start;
 *      和右下角的行endY=rows-1-start;
 *3、总共打印分为四步，a：从左到右
 *                              b:从上到下：末端列号>起始列号   endY>start
 *                              c:从右到左: 在b步下，还要末端行号>起始行号， endX>start
 *                              d:从下到上:在第c步的条件下，必须满足，末端列号>=起始列好+2
 *                              可以想如果有的情况下会怎样，如果没有的情况下会怎么样；
 * @author LemonLin
 * @Description :PrintMatrix20
 * @date 2018/3/7-16:52
 */
public class PrintMatrix20 {
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

        PrintMatrix20 printMatrix20= new PrintMatrix20();
        ArrayList<Integer> arrayList = printMatrix20.printMatrix(text);
        for (int i =0;i<=arrayList.size()-1;++i){
            System.out.println(arrayList.get(i));
        }

        System.out.println("二维数组的列的个数"+test1.length);
        System.out.println("二维数组的行的个数"+test1[0].length);
    }
}
