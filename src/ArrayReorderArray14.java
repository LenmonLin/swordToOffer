/**
 *题目描述
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶
 * 数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * 考虑可拓展性,应该抽离出判断条件，形成函数
 * 基本思路是：
 * 1、不能利用快速排序，题目要求奇数和奇数，偶数和偶数之间的相对位置不变，如果使用快速排序，不能
 * 保证相对顺序不变。
 * 2、方法一：利用空间换时间：遍历所有的奇数先存储，后面再用偶数存储，本题采用这个解法
 * 3、方法二：利用插入排序，当时这样做最坏的空间复杂度有O(n^2),不推荐。
 * @author LemonLin
 * @Description :ReorderArray
 * @date 2018/2/28-21:09
 */
public class ArrayReorderArray14 {


    public void reOrderArray(int [] array) {

        if (array == null || array.length == 0) {
            return;
        }
        //存放奇数的数组
        int [] oddArray = new int[array.length];
        //存放偶数的数组
        int [] evenArray = new  int[array.length];
        //记录奇数个数，偶数个数为array.length-oddCount
        int oddCount = 0;

        for (int i=0,j=0,k=0;i<array.length;i++){
            if (isEven(array[i])){
                evenArray[j++]=array[i];
            }else {
                oddArray[k++]=array[i];
                oddCount++;
            }
        }
        //先将奇数存放到原先数组中，再存放偶数数组
        for (int i=0,j=0;i<array.length;i++,j++){
            if (j<oddCount) {
                array[i] = oddArray[j];
            }
        }
        for (int i=oddCount,j=0;i<array.length;i++,j++){
            if (j<array.length-oddCount){
                array[i]=evenArray[j];
            }
        }
    }

    //判断是否是偶数，是返回true
    public boolean isEven(int x){
        if (x%2 == 0 )
            return true;
        else
            return false;
    }
    //测试代码
    public static void main(String[] args) {
        int [] array={1,2,3,4,5,6,7};
        ArrayReorderArray14 reorderArray14 = new ArrayReorderArray14();
        reorderArray14.reOrderArray(array);
        for (int i=0;i<array.length;++i){
            System.out.println(array[i]);
        }
    }
}
