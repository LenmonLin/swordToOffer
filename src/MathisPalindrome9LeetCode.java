import java.util.Scanner;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 思路一：直接将数字转换为字符串，也不会发生大数问题，有效
 * 思路二：考虑进阶问题：如果不转化为字符串。取出首位，再取出末位进行比较，然后数字少了两位
 * 再取出首位和末位比较，如此反复。
 * 首先用一个变量记录数字的最高位
 * 比如12321，可以标记为help为10000
 * 第一个末位为1，第一个首位为12321/10000=1
 * 接下来我们需要计算232是否为回文。怎么计算呢。
 * 我们需要去掉首位和末位。
 * 可以采用x % help / 10的方式
 * 12321%10000==2321可以将最高位去掉，然后2321/10==232可以将最低为去掉。
 * 最后将help/100，因为12321去掉两位了。
 * 思路三：把数字分成两半，翻转其中一半，然后比较是否相等，这么处理需要考虑数字是偶数个还是
 * 奇数个
 * @author LemonLin
 * @Description :MathisPalindrome9LeetCode
 * @date 19.9.11-16:02
 */
public class MathisPalindrome9LeetCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(new MathisPalindrome9LeetCode().isPalindrome(x));
    }
    public boolean isPalindrome(int x) {
        if(x<0)return false;
        //用来记录输入的位，如果输入123，div=100,输入12，div=10
        int div = 1;
        int y=x;
        //要注意这里计算位数的循环判断条件是y>=10
        while (y>=10){
            y/=10;
            div*=10;
        }
        while (x>0){
            int left = x/div;
            int right = x%10;
            if (left!=right)return false;
            x = x%div/10;
            div /=100;
        }
        return true;
    }
}
