/**
 * @author LemonLin
 * @Description :Duplicate51
 * @date 2018/9/20-11:29
 *
 * 题目描述：数组中重复的数字:
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 *
 *
 * 思路一：时间复杂度O(n),空间复杂度O(n)
 * 利用一个新开辟的数组来存储每次读取的给定的数组值，比如给定数组为{4，2，1，5，6，0，1}
 * 那么读取到4，就在新数组的下标为4的地方存入4，读取2，存入2，以此类推，存入之前判断一下数组中是否
 * 已经存在这个数，如果存在就找到了对应的一个重复的数
 *
 *
 * 思路二：时间复杂度O(n),空间复杂度O(1)
 *
 * 如果这个数组中没有重复的数字，那么排序完数字i将出现在下标为i的位置；
 * 从头到尾依次扫描这个数组中的每个数字。当扫描到下标为i的数字时，用一个m来暂存，对比是不是等于i.
 *       如果是，接着扫描下一个数字。
 *       如果不是，再拿它和第m个数字进行比较。
 *              如果它和第m个数字相等，就找到了一个重复的数字
 *              如果不相等，就把第i个数字和第m个数字交换，把m放到属于它的位置
 */
public class ArrayDuplicate51 {

    /**
     *@Description
     *@params numbers:     an array of integers
     *  *      length:      the length of array numbers
     *  *       duplication: (Output) the duplicated number
     *  *     in the array number,length of duplication array is 1,so using duplication[0]
     *@author LemonLin
     *@date  2018/9/20
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {


        int temp=0;
        for (int i=0;i<numbers.length;i++){
            temp = numbers[i];
            if (temp == i){
                continue;
            }else if (temp == numbers[temp]){
                duplication[0]=temp;
                return true;
            }else {
                int temp2=0;
                temp2 = numbers[temp];
                numbers[temp]=numbers[i];
                numbers[i] = temp2;
                i--;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        int test[]={2,3,1,0,2,5,3};
        ArrayDuplicate51 arrayDuplicate51 =new ArrayDuplicate51();

        int duplicate[]={0};
        arrayDuplicate51.duplicate(test,test.length,duplicate);

        System.out.println(duplicate[0]);

    }
}
