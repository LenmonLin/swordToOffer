/**
 * @author LemonLin
 * @Description :AddWithoutSymbol47
 * @date 2019/3/4-15:50
 *
 * 题目描述:
 *          写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号
 * 解法：分三步进行。
 * 1、对各位进行相加，不进位，其实就是异或操作，符号^
 * 2、做进位的统计，就是进行与运算，运算结果需左移一位，表示进位后的结果
 * 3、n1&n2是查看有没有进位位了，如果有，需要重复step1、step2；如果没有，即
 * 返回num1^num2的结果。
 *
 */
public class AddWithoutSymbol47 {

    public int Add(int num1,int num2) {
        while (num2!=0) {
            int temp = num1^num2;
            num2 = (num1&num2)<<1;
            num1 = temp;
        }
        return num1;
    }

    public static void main(String[] args) {

        AddWithoutSymbol47 addWithoutSymbol47 = new AddWithoutSymbol47();
        int num = addWithoutSymbol47.Add(5,17);
        System.out.println(num);

    }

}

