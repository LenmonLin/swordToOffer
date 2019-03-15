/**
 * @author LemonLin
 * @Description :Fibonacci09
 * @date 2018/2/25-11:48
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *解题思路：
 *  没有什么难度，就是不要用递归去处理，效率比较低
 *  正确的做法是保留中间的结果
 */
public class MathFibonacci09 {
    public int Fibonacci(int n) {
        int result[]={0,1};
        if (n<=2){
            return result[n-1];
        }
        int fibonacciOne = 0;
        int fibonacciTwo = 1;
        int fibonacciResult = 0;
        for (int i=3;i<=n;++i){
            fibonacciResult = fibonacciOne+fibonacciTwo;
//           注意这里不用弄错了额，是Two赋值给One,这样数列才能前进，不能把One付给Two;
            fibonacciOne = fibonacciTwo;
            fibonacciTwo = fibonacciResult;
        }
        return fibonacciResult;
    }
    //测试代码
    public static void main(String[] args) {
        MathFibonacci09 mathFibonacci09 = new MathFibonacci09();
        int n=1;
        int fibonacciResult = mathFibonacci09.Fibonacci(n);
        System.out.println(fibonacciResult);
    }
}
