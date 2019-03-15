import java.util.ArrayList;
/**
 * @author LemonLin
 * @Description :TwoNumbersWithSum41_1
 * @date 2018/5/14-21:35
 * 题目描述
        输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
        如果有多对数字的和等于S，输出两个数的乘积最小的。
 *  思路是设两个指针，分别指向数组首部和数组末尾。因为是递增数组，如果两个指针之和小于给定的值
 *  则将首部指针向后移动，如果两个指针之和大于给定的值，则将尾部指针向前移动
 */
public class ArrayTwoNumbersWithSum41_1 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        //存储所求和的arraylist
        ArrayList arrayList = new ArrayList();
        int start = 0;
        int end = array.length-1;
        while (start<end){
            if (array[start]+array[end] == sum){
                arrayList.add(array[start]);
                arrayList.add(array[end]);
                break;
            }
            if (array[start]+array[end] < sum){
                start++;
            }
            if (array[start]+array[end] > sum){
                end--;
            }
        }
        return arrayList;
    }
    public static void main(String[] args) {
        int[] testArray = {1,2,3,4,7,11,15};
        ArrayTwoNumbersWithSum41_1 arrayTwoNumbersWithSum41_1 = new ArrayTwoNumbersWithSum41_1();
        ArrayList<Integer> arrayList = arrayTwoNumbersWithSum41_1.FindNumbersWithSum(testArray, 15);
        for (int i=0;i<arrayList.size();++i){
            System.out.println(arrayList.get(i));
        }
    }
}
