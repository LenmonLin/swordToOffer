/**
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都
 * 按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中
 * 是否含有该整数
 *
 * 解题思路：
 *      设要查找的数字是target
 *      if(target == 数组右上角的数)
 *          return true;
 *       else if(target >数组右上角的数){
 *           去掉数组右上角所在的行
 *           continue;
 *       }else if(target < 数组右上角的数){
 *           去掉数组右上角所在的列
 *             continue;
 *        }
 *
 *        以此类推
 */
public class ArrayFindInPartiallySortedMatrix03 {


    public boolean Find(int target, int [][] array) {

        //行数
        int rowCount = array.length;
        //列数
        int colCount =array[0].length;
        for (int i =0,j=colCount-1;i<rowCount&&j>=0;){
            if (target == array[i][j]){
                return true;
            }
            if (target > array[i][j]){
                i++;
                //这里的continue 很重要，否则会出错，继续往下比较
                continue;
            }
            if (target< array[i][j]){
                j--;
                continue;
            }
        }

        return  false;

    }


    public static void main(String[] args){
        int [][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        ArrayFindInPartiallySortedMatrix03 arrayFind =new ArrayFindInPartiallySortedMatrix03();
        boolean find = arrayFind.Find(14, array);

        System.out.println(find);
    }
}

