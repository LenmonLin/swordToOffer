/**
*  实现 pow(x, n) ，即计算 x 的 n 次幂函数。
*  示例 1:
*  输入: 2.00000, 10
*  输出: 1024.00000
*  示例 2:
*  输入: 2.10000, 3
*  输出: 9.26100
*  示例 3:
*  输入: 2.00000, -2
*  输出: 0.25000
*  解释: 2-2 = 1/22 = 1/4 = 0.25
*  说明:
*  -100.0 < x < 100.0
*  n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * @author LemonLin
 * @Description :BinarySearchmyPow50LeetCode
 * @date 20.2.5-11:34
 * 思路：用快速幂的方法，参考：
 * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode/
 */
public class BinarySearchmyPow50LeetCode {
    public double myPow(double x, int n) {
        if (n<0){
            x=1/x;
            n=-n;
        }
        return myPowHelper(x,n);
    }
    private double myPowHelper(double x, int n){
        if (n==0){
            return 1.0;
        }
        double half =myPowHelper(x,n/2);
        if (n%2==0){
            return half*half;
        }else {
            return half*half*x;
        }
    }
}
