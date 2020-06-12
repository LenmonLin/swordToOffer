/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * @author LemonLin
 * @Description :ArrayplusOne66LeetCode
 * @date 20.3.31-23:11
 * bug1:
 * 输入:
 * [9]
 * 输出
 * [0]
 * 预期结果
 * [1,0]
 * bug2:
 * 输入:
 * [8,9,9,9]
 * 输出
 * [1,0,0,0,0]
 * 预期结果
 * [9,0,0,0]
 * 思路：看着很简单，但是还有有一点想头的，不用考虑那种进位一直进的那种。因为只
 * 进到一个位，不是等于0就可以退出了。如果是一直进的那种，要重新考虑，什么意思，
 * 看不懂没关系，举几个例子。
 * 例子1:998，这种，就是只要末尾加1，末尾的数不是等于0，也就是没有进位，可以末尾
 * 加1之后直接退出，因为程序已经操作完毕了。
 * 例子2：929，这种末尾加一之后，要进位变成930，那么从后往前遍历，最后一位加1，
 * 之后为0，进入下一个循环，2+1=3，不等于0，退出循环结束程序，这里不用考虑设置
 * 什么进位位之类的，因为只加一次，自身位加一即可。
 * 例子3：999，这种就需要遍历结束之后，重新建立一个数组，第一位为1，其他为0.
 */
public class ArrayplusOne66LeetCode {
    //方法2，非常简单直白，不容易出错。
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    //自己写的太复杂了，放弃
    public int[] plusOne(int[] digits) {
        int carry =0;
        int temp = digits[digits.length-1]+1;
        carry = temp/10;
        digits[digits.length-1] = temp%10;
        for (int i= digits.length-2;i>0;i--){
            temp = digits[i]+carry;
            carry = temp/10;
            digits[i] = temp%10;
        }
        if (carry==1&&digits[0]==9){
            int [] result = new int[digits.length+1];
            result[0]=1;
            for (int i=1;i<=digits.length;i++){
                result[i]=0;
            }
            return result;
        }else {
            digits[0]=digits[0]+carry;
        }
       return digits;
    }

    public static void main(String[] args) {
        int [] digits ={1,2,3};
        int[] ints = new ArrayplusOne66LeetCode().plusOne2(digits);
        for (int i=0;i<ints.length;i++){
            System.out.println(ints[i]);
        }
    }
}
