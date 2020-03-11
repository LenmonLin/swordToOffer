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
 * 貌似和二分法关系不大：先想一想暴力解法：不断相乘，迭代即可。O(n)
 * 还有一个更快的：假定我们已经得到了 x ^ {n / 2}的结果，并且我们现在想得到 x ^ n
 *的结果。我们令 A 是 x ^ {n / 2}的结果，我们可以根据 n 的奇偶性来分别讨论 x ^ n
 *的值。如果 n 为偶数，我们可以用公式 (x ^ n) ^ 2 = x ^ {2 * n}来得到 x ^ n = A * A。
 * 如果 n 为奇数，那么 A * A = x ^ {n - 1}。直观上看，我们需要再乘一次 x，即 x ^ n
 * = A * A * x。该方法可以很方便的使用递归实现。我们称这种方法为 "快速幂"，
 *这里还要注意一个就是n<0的处理方式：需要令x=1/x;n=-n
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
