/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键
 * 字及条件判断语句（A?B:C）。
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 * 限制：
 * 1 <= n <= 10000
 * @author LemonLin
 * @Description :面试题64sumNums
 * @date 20.6.2-15:30
 * 思路：https://leetcode-cn.com/problems/qiu-12n-lcof/solution/mian-shi-ti-64-qiu-1-2-nluo-ji-fu-duan-lu-qing-xi-/
 * 利用递归实现循环操作，利用&&的短路特性实现if判断操作
 * Java 中，为构成语句，需加一个辅助布尔量 x ，否则会报错；
 * Java 中，开启递归函数需改写为 sumNums(n - 1) > 0 ，此整体作为一个布尔量输出，
 * 否则会报错；初始化变量 res 记录结果。（ Java 可使用第二栏的简洁写法，不用借助变量 res ）。
 */
public class 面试题64sumNums {
    //注意这里要全局变量
    int result = 0;
    public int sumNums(int n) {
        boolean temp = n>1 && sumNums(n-1)>0;
        result+=n;
        return result;
    }
}
