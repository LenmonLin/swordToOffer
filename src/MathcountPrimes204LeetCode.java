import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * @author LemonLin
 * @Description :MathcountPrimes204LeetCode
 * @date 20.7.1-15:00
 * 思路：
 * 参考：https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
 * 1、暴力法：超出时间限制,根据质数的定义，只能被1和本身整除的才是质数。
 * bug1:
 * 输入:
 * 2
 * 输出
 * 1
 * 预期结果
 * 0
 *2、举个例子，假设 n = 12。
 * 12 = 2 × 6
 * 12 = 3 × 4
 * 12 = sqrt(12) × sqrt(12)
 * 12 = 4 × 3
 * 12 = 6 × 2
 * 可以看到，后两个乘积就是前面两个反过来，反转临界点就在 sqrt(n)。
 * 换句话说，如果在 [2,sqrt(n)] 这个区间之内没有发现可整除因子，就可以直接断定 n
 * 是素数了，因为在区间 [sqrt(n),n] 也一定不会发现可整除因子。
 * 所以可以调整外循环循环遍历的变量：
 *      for (int i = 2; i * i<n ; i++) {
 *             if(helper(i)){
 *                 count++;
 *             }
 *         }
 * 3、厄拉多塞筛法
 * 简单介绍一下厄拉多塞筛法。厄拉多塞是一位古希腊数学家，他在寻找素数时，采用了
 * 一种与众不同的方法：先将2－N的各数放入表中，然后在2的上面画一个圆圈，然后划去
 * 2的其他倍数；第一个既未画圈又没有被划去的数是3，将它画圈，再划去3的其他倍数；
 * 现在既未画圈又没有被划去的第一个数 是5，将它画圈，并划去5的其他倍数……依次类
 * 推，一直到所有小于或等于N的各数都画了圈或划去为止。这时，表中画了圈的以及未划
 * 去的那些数正好就是小于 N的素数。
 * 参考：https://blog.csdn.net/gavinming/article/details/7212980
 * 和https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
 */
public class MathcountPrimes204LeetCode {

    //厄拉多塞筛法
    public int countPrimes(int n) {
        int count =0;
        boolean [] isPrimes = new boolean[n];
        //先全初始化为true
        Arrays.fill(isPrimes,true);
        for (int i = 2; i*i<n ; i++) {
            if (isPrimes[i]){
                //重点在这里，i的k倍都设置成false
                for (int k=2;k*i<n;k++){
                    isPrimes[k*i]= false;
                }
            }
        }
        for (int i = 2; i <n ; i++) {
            if (isPrimes[i]){
                count++;
            }
        }
        return count;
    }

    //调整外循环变量
    public int countPrimes2(int n) {
        int count =0;
        //解决bug1:题目要求小于n,所以n不能算进去
        //从2开始，1不能算质数。
        for (int i = 2; i * i<n ; i++) {
            if(helper(i)){
                count++;
            }
        }
        return count;
    }
    //暴力法，超出时间限制
    public int countPrimes1(int n) {
        int count =0;
        //解决bug1:题目要求小于n,所以n不能算进去
        //从2开始，1不能算质数。
        for (int i = 2; i <n ; i++) {
            if(helper(i)){
                count++;
            }
        }
        return count;
    }
    private boolean helper(int input){
        for (int i = 1; i <=input ; i++) {
            //这里一定要注意i!=1&&i!=input中间取的是&& 而不是|| ,容易出错。
            if (input%i==0&&i!=1&&i!=input){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n=10;
        System.out.println(new MathcountPrimes204LeetCode().countPrimes(n));
    }
}
