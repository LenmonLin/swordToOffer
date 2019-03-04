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
public class Sumsolution46 {


    public int SumSolution(int n) {
        int sum =n;
        //java 中&&符号两边需要的是表达式
        boolean ans= n>0 && ((sum+=SumSolution(n-1))>0);
        return sum;
    }

    public static void main(String[] args) {
        Sumsolution46 sumsolution46 = new Sumsolution46();
        int sum = sumsolution46.SumSolution(5);
        System.out.println(sum);
    }
}
