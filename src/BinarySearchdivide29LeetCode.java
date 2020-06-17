/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法
 * 和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及
 * truncate(-2.7335) = -2
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题
 * 中，如果除法结果溢出，则返回 231 − 1。
 * @author LemonLin
 * @Description :BinarySearchdivide29LeetCode
 * @date 20.2.5-11:33
 * 思路：参考https://leetcode-cn.com/problems/divide-two-integers/solution/
 * po-su-de-xiang-fa-mei-you-wei-yun-suan-mei-you-yi-/
 * 1、主体思路：举个例子：11 除以 3 。
 * 首先11比3大，结果至少是1， 然后让3翻倍，就是6，发现11比3翻倍后还要大，那么
 * 结果就至少是2了，那让这个6再翻倍，得12，11不比12大，所以无需让刚才的最小解
 * 2也翻倍为4。但是知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个
 * 数是多少呢？让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是
 * 除法，递归出现了。
 * 2、遇到的问题：越界问题.
 * 本题的越界问题，暴露出一个很大问题之前没弄懂。
 * 原码：就是表示真值，是日常文字手写使用的值。字长八位的手写1111,1111表示-127
 * 手写0111,1111表示127，所以原码表示真值范围为127~-127.
 * 补码：也就是机器码，在程序上写的就是补码，这点我一直以为是原码，然后idea编译的
 * 时候自动转换为补码，其实不是的，你写出来的代码，就是补码。为什么平时没有感觉，
 * 一个是因为平时写16进制写的比较少，还有一个原因是写负数写的比较少，正数的情况下
 * 补码和真值是一样的。所以没发现。
 * 举个例子：Integer.MIN_VALUE 看源码会发现为0x80000000，表示-2147483648。
 * 这个原码是用32位是表示不出来的。这个是补码的特征位。也就是补码情况下0x10000
 * 表示最小值。这里只举例负数，因为负数和真值不一样，所以需要特别关注一下，如果写
 * 0x80000001,真值表示为-1，补码，机器表示-2147483647。所以总结一下，如果平时
 * 和人交流，手写是用真值，如果是在代码上的，你写的代码就是补码，特别是负数，一定
 * 要转换一下思维。这一点一定要意识到。
 * 本题的问题，为了处理被除数和除数不是一个符号，需要把两个转换为一个符号，转换的
 * 过程就可能遇到溢出问题，因为补码情况下32位的int能够表示的范围为
 * 2147483647到-2147483648，所以可以看出，负数比正数多表示一个位，也就是当
 * 输入是-2147483648，无法转换成正数，会发生溢出。
 * 解决方式，把所有输入都转换为负数处理。因为负数表示的范围比较大。正数转成负数，
 * 不会发生溢出。即使输入最大2147483647，改变符号，转成负数为-2147483647。
 * 也在表示范围之内。
 *3、关于整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及
 *  truncate(-2.7335) = -2。
 *  可以用这样处理，如果被除数小于除数返回0.
 */
public class BinarySearchdivide29LeetCode {

    //这种解法解决数值太大溢出问题，不用divide1的long类型解法，还没写，有空再写
    public int divide(int dividend, int divisor) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        return 0;
    }

    //这里传入的是已经将被除数和除数都转换为负数，
    private int helper(int dividend,int divisor){
        return 0;
    }

    /**
     *解法1bug:
     * 输入:
     * -2147483648
     * 2
     * 输出
     * 0
     * 预期结果
     * -1073741824
     */
    //divide1和helper1是把被除数和除数都转换为正数的处理方式，这个是正常思路，对
    // 于可能发生的溢出，暂时用long类型处理
    public int divide1(int dividend, int divisor) {
        //题目要求溢出返回：2147483647，溢出只有一种情况：被除数为-2147483648，
        // 除数为-1，这种情况会溢出。特殊判断一下
        if (divisor==-1&&dividend==Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        //除数等于1或者-1也是特殊情况，特殊处理。
        if(divisor == 1) return dividend;
        if(divisor == -1) return -dividend;

        long result =0;
        //判断两个数是否异号。
        boolean sign =false;
        if (dividend>0&&divisor<0||dividend<0&&divisor>0){
            sign = true;
        }
        //都统一成正数。需要先把dividend和divisor用long保存一下，这样就变成了long型，
        // 求相反数的时候就不会溢出。
        long a = dividend;
        long b = divisor;
         a=a>0?a:-a;
         b=b>0?b:-b;
        result = helper1(a,b);
        if (sign) {
            result = -result;
        }
        return (int)result;
    }
    //这里传入的是已经将被除数和除数都转换为正数，
    private long helper1(long dividend,long divisor){
        //为了解决第三点
        if (dividend<divisor){
            return 0;
        }
        long count =1;
        //因为递归中还需要使用divisor,所以需要暂时保存起来
        long tempDivisor = divisor;
        //这下面是精髓的部分
        while (tempDivisor+tempDivisor<dividend){
            count=count+count;
            tempDivisor = tempDivisor+tempDivisor;
        }
        return count+helper1(dividend-tempDivisor,divisor);
    }
    public static void main(String[] args) {
        int dividend = -2147483648;
        int divisor = 2;
        System.out.println(new BinarySearchdivide29LeetCode().divide1(dividend, divisor));
    }
}
