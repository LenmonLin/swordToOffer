/**
 * @author LemonLin
 * @Description :Sumsolution46
 * @date 2019/3/4-11:08
 *
 * 题目描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等
 * 关键字及条件判断语句（A?B:C）。
 *
 * 解法思路：不能用循环解题了，就用递归代替，利用逻辑与的短路特性实现递归终止。
 *
 */
public class MathSumsolution46 {


    public int Sum_Solution(int n) {
        int sum =n;
        //java 中&&符号两边需要的是表达式
        boolean ans= n>0 && ((sum+=Sum_Solution(n-1))>0);
        return sum;
    }

    public static void main(String[] args) {
        MathSumsolution46 mathSumsolution46 = new MathSumsolution46();
        int sum = mathSumsolution46.Sum_Solution(5);
        System.out.println(sum);
    }
}
